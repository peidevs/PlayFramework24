package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by B on 15-07-09.
 */

@Entity
public class Task extends Model {

    @Id
    public Long id;

    public String title;

    public Boolean isDone;


    private Task (){}

    private static Finder<Long, Task> find = new Finder<>(Long.class, Task.class);


    public static Task createTask(String title){

        Task newTask = new Task();

        newTask.title = title;
        newTask.isDone = false;

        newTask.save();

        return newTask;

    }

    public void updateTask(String title, Boolean status){

        this.title = title;
        this.isDone = status;

        this.update();
    }

    public static Task findById(Long id){
        return find.byId(id);
    }

    public static List<Task> findAll() {
        return find.all();
    }
}
