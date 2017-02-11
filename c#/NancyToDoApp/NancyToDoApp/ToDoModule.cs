using Nancy;
using Nancy.ModelBinding;
using NancyToDoApp.Models;

namespace NancyToDoApp
{
    public class ToDoModule : NancyModule
    {
        public ToDoModule()
        {
            var toDoService = new ToDoService();
            // List all items in the ToDo
            Get["/"] = _ => View["ToDoList.html", toDoService.GetList()];
            // Page for adding items to the ToDo
            Get["/new"] = _ => View["NewItem.html"];
            // Post for adding the new items to the ToDo
            Post["/"] = _ =>
            {
                ToDoItem newItem = this.Bind();
                toDoService.AddNewItem(newItem);
                return View["ToDoList.html", toDoService.GetList()];
            };
            // Get item
            Get["/{id}"] = _ => "Get specific item";
            // Update item
            Patch["/{id}"] = _ => "Update item";
            // Delete given item
            Delete["/{id}"] = _ => "Delete item";
            // Page for updating the info of item
            Get["/{id}/edit"] = _ => "Page for update given item";
            // Update all of the item info
            Put["/{id}"] = _ => "Update the item info";
        }
    }
}
