package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity 
public class User extends Model {
    @Id
    @Constraints.Min(10)
    public Long id;

    public String name;

    public String password;

    public boolean admin;

    public static Finder<Long, User> find = new Finder<Long,User>(
        Long.class, User.class
    );

    /** 新規ユーザ登録 */
    public static void create(User user){
        user.save();
    }

    /** ユーザ認証 */
    public static User authenticate(String name, String password){
        return find.where().eq("name", name).eq("password", password).findUnique();
    }

    /** 名前から権限を確認する */
    public static Boolean checkAdmin(String name){
        User user = find.where().eq("name", name).findUnique();
        return user.admin;
    }

    /** ユーザ名からidを確認する */
    public static Long checkId(String name){
        User user = find.where().eq("name", name).findUnique();
	return user.id;
    }

    public static void update(User newUser) {
	newUser.update();
    }
}
