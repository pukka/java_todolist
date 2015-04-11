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
    @Security.Authenticated(Secured.class)
    public static Result showTasks() {
        return ok(
	    views.html.index.render(Task.all(), taskForm)
	);
    }

    public static Result newTask() {
        Form<Task> filledForm = taskForm.bindFromRequest();
        if(filledForm.hasErrors()) {
	    return badRequest(
	        views.html.index.render(Task.all(), filledForm)
	    );
        } else {
	    Task.create(filledForm.get());
	    return redirect(routes.Application.showTasks());
        }
    }

    public static Result task(Long id){
	 return ok(
	     views.html.select.render(models.Task.select(id), taskForm)
	 );
    }

    public static Result update(Long id){
        Form<Task> filledForm = taskForm.bindFromRequest();
            if(filledForm.hasErrors()) {
                return badRequest(
	            views.html.select.render(models.Task.select(id), filledForm)
	        );
            } else {
                Task.change(filledForm.get());
                return redirect(routes.Application.showTasks());
            }
    }

    public static Result deleteTask(Long id) {
        Task.delete(id);
	return redirect(routes.Application.showTasks());
    }
}
