using Nancy;

namespace NancyToDoApp
{
    public class ToDoModule : NancyModule
    {
        public ToDoModule()
        {
            // List all items in the ToDo
            Get["/"] = _ => "List all the items";
            // Page for adding items to the ToDo
            Get["/new"] = _ => "New item page";
            // Post for adding the new items to the ToDo
            Post["/"] = _ => "Adding new item to the to do";
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
