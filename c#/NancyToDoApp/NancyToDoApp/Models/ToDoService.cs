using System;
using System.Linq;

namespace NancyToDoApp.Models
{
    public class ToDoService
    {
        private static ToDoList ToDoList;

        public ToDoService()
        {
            ToDoList = new ToDoList();
            ToDoList.Items.Add(
                new ToDoItem
                {
                    Description = "Long description of Item 1",
                    DueDate = DateTime.Now,
                    IsDone = false,
                    Title = "Item 1"
                });
            ToDoList.Items.Add(
                new ToDoItem
                {
                    Description = "Long description of Item 2",
                    DueDate = DateTime.Now,
                    IsDone = false,
                    Title = "Item 2"
                });
            ToDoList.Items.Add(
                new ToDoItem
                {
                    Description = "Long description of Item 2",
                    DueDate = DateTime.Now,
                    IsDone = false,
                    Title = "Item 2"
                });
        }

        public ToDoList GetList()
        {
            return ToDoList;
        }

        public void AddNewItem(ToDoItem newItem)
        {
            ToDoList.Items.Add(newItem);
        }

        public ToDoItem GetItemByTitle(string title)
        {
            return ToDoList.Items.FirstOrDefault(x => x.Title == title);
        }
    }
}
