package controllers;

import models.Task;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result getTasks(){
        List<Task> tasks= Task.findAll();
        return ok(toJson(tasks));
    }

    public Result updateTask(){

        String title = request().body().asJson().get("title").asText();

        Task task = Task.createTask(title);

        return ok(toJson(task));
    }

}
