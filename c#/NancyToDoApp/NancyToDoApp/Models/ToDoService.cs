using System;

namespace NancyToDoApp.Models
{
    public class ToDoService
    {
        public ToDoList GetList()
        {
            var toDoList = new ToDoList();
            toDoList.Items.Add(
                new ToDoItem
                {
                    Description = "Long description of Item 1",
                    DueDate = DateTime.Now,
                    IsDone = false,
                    Title = "Item 1"
                });
            toDoList.Items.Add(
                new ToDoItem
                {
                    Description = "Long description of Item 2",
                    DueDate = DateTime.Now,
                    IsDone = false,
                    Title = "Item 2"
                });
            toDoList.Items.Add(
                new ToDoItem
                {
                    Description = "Long description of Item 2",
                    DueDate = DateTime.Now,
                    IsDone = false,
                    Title = "Item 2"
                });
            return toDoList;
        }
    }
}
