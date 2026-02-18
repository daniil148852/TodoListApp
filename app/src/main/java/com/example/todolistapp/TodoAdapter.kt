package com.example.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<TodoItem>,
    private val onDeleteClick: (TodoItem) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoText: TextView = itemView.findViewById(R.id.todoTextView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoText.text = todo.text
        
        if (todo.isCompleted) {
            holder.todoText.paintFlags = holder.todoText.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.todoText.paintFlags = holder.todoText.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClick(todo)
        }

        holder.itemView.setOnClickListener {
            todo.isCompleted = !todo.isCompleted
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = todos.size

    fun addTodo(text: String) {
        todos.add(0, TodoItem(text = text))
        notifyItemInserted(0)
    }

    fun deleteTodo(todo: TodoItem) {
        val position = todos.indexOf(todo)
        if (position != -1) {
            todos.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
