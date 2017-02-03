using System.Collections.Generic;

namespace NancyToDoApp.Models
{
    public class ToDoList
    {
        public ToDoList()
        {
            Items = new List<ToDoItem>();
        }

        public List<ToDoItem> Items { get; }
    }
}
