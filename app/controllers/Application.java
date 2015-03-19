package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /** Tasks */
    public static Result showTasks() {
        return TODO;
    }

    public static Result createTask() {
        return TODO;
    }

    public static Result deleteTask(Long id) {
        return TODO;
    }
}
