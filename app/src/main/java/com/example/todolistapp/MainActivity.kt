package com.example.todolistapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoInput: EditText
    private lateinit var addButton: Button
    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var todoAdapter: TodoAdapter
    private val todos = mutableListOf<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoInput = findViewById(R.id.todoInput)
        addButton = findViewById(R.id.addButton)
        todoRecyclerView = findViewById(R.id.todoRecyclerView)
        emptyView = findViewById(R.id.emptyView)

        setupRecyclerView()
        setupAddButton()
        updateEmptyView()
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(todos) { todo ->
            todoAdapter.deleteTodo(todo)
            updateEmptyView()
        }

        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        todoRecyclerView.adapter = todoAdapter
    }

    private fun setupAddButton() {
        addButton.setOnClickListener {
            val text = todoInput.text.toString().trim()
            if (text.isNotEmpty()) {
                todoAdapter.addTodo(text)
                todoInput.text?.clear()
                updateEmptyView()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateEmptyView() {
        if (todos.isEmpty()) {
            todoRecyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            todoRecyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }
}
