function Task(data) {
    this.id = ko.observable(data.id);
    this.title = ko.observable(data.title);
    this.isDone = ko.observable(data.isDone);
}

function TaskListViewModel() {
    // Data
    var self = this;
    self.tasks = ko.observableArray([]);
    self.newTaskText = ko.observable();
    self.incompleteTasks = ko.computed(function() {
        return ko.utils.arrayFilter(self.tasks(), function(task) { return !task.isDone() && !task._destroy });
    });

    // Operations
    self.addTask = function() {
        var t = new Task({ title: this.newTaskText()});
        $.ajax({
            url: "/task" ,
            data: ko.toJSON(t),
            type: "post",
            contentType: "application/json",
            success: function(result) {
                 self.tasks.push(t);
                 self.newTaskText("");
            }
        });
    };
    self.removeTask = function(task) { self.tasks.destroy(task) };

    // Load initial state from server, convert it to Task instances, then populate self.tasks
    $.getJSON("/tasks", function(allData) {
        var mappedTasks = $.map(allData, function(item) { return new Task(item) });
        self.tasks(mappedTasks);
    });
}

$(function(){
    ko.applyBindings(new TaskListViewModel());
});
