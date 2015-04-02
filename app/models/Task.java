package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model{
    @Id
    public Long id;

    @Required
    public String label;

    public static Finder<Long, Task> find = new Finder(
        Long.class, Task.class
    );

    public static List<Task> all() {
        return find.all();
    }

    public static void create(Task task) {
	task.save();
    }

    public static void change(Task newTask) {
	newTask.update();
    }

    public static void delete(Long id) {
	find.ref(id).delete();
    }

    public static Task select(Long id) {
	return find.ref(id);
    }
}
