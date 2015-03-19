package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import models.*;
import views.html.*;

public class Application extends Controller {

    static Form<Task> taskForm = Form.form(Task.class);

    public static Result index() {
        return redirect(routes.Application.showTasks());
    }

    /** Tasks */
    public static Result showTasks() {
        return ok(
	    views.html.index.render(Task.all(), taskForm)
	);
    }

    public static Result newTask() {
        return TODO;
    }

    public static Result deleteTask(Long id) {
        return TODO;
    }
}
