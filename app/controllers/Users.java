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

    /** ログインページ */
    public static Result login(){
	List<User> user = null;
	user = User.find.all();
	if(user.isEmpty()){
	    return redirect( routes.Users.first() );
	}
        return TODO;
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
}
