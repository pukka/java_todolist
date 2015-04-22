package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;

import views.html.*;

import models.*;

public class Users extends Controller {
    /** ユーザ作成用のフォーム */
    static Form<User> userForm = Form.form(User.class);
    /** ログイン用のフォーム */
    static Form<Login> loginForm = Form.form(Login.class);

    /** ログインページ */
    public static Result login(){
	List<User> user = null;
	user = User.find.all();
	if(user.isEmpty()){
	    return redirect( routes.Users.first() );
	}
        return ok(login.render(loginForm));
    }

    /** ユーザが誰もいなければ呼び出される */
    public static Result first() {
        return ok( first.render(userForm) );
    }

    /** ユーザ追加処理 */
    public static Result addUser() {
        Form<User> filledForm = userForm.bindFromRequest();
      	if(filledForm.hasErrors()){
            return badRequest(first.render(filledForm));
   	}
        User.create(filledForm.get());
        session().clear();
	session("name",filledForm.get().name);
	return redirect(routes.Application.showTasks());
    }

    /**
     *  フォームのバインド後、エラーがあればbadRequest
     *  問題なければセッションにname記録
     */
    public static Result authenticate() {
        Form<Login> filledForm = loginForm.bindFromRequest();
	if(filledForm.hasErrors()) {
            return badRequest(login.render(filledForm));
        }
	session().clear();
        session("name",filledForm.get().name);
        return redirect(routes.Application.showTasks());
    }

    public static Result mypage(Long id){
        return ok(mypage.render(User.find.ref(id),userForm));
    }

    /** マイページ更新 */
    public static Result updateMypage(Long id){
        Form<User> filledForm = userForm.bindFromRequest();
	    if(filledForm.hasErrors()) {
	        return badRequest(mypage.render(User.find.ref(id), filledForm));
            } else {
		User.update(filledForm.get());
		return redirect(routes.Application.showTasks());
	    }
    }

    /** ログアウト処理 */
    public static Result logout() {
        session().clear();
        return redirect(routes.Users.login());
    }

    /** ログイン用のクラス */
    public static class Login {
        public String name;
	public String password;

	public String validate() {
	    if (User.authenticate(name, password) == null) {
	        return "パスワード、またはユーザ名が有効ではありません。";
            }
	    return null;
        }
    }
}
