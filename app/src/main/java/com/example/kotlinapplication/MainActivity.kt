package com.example.kotlinapplication

import TodoAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var adapter: TodoAdapter;
    private lateinit var binding: ActivityMainBinding
//    data bind the activity_main to the mainactivity then provide the adapter and the layout manager
//    make object of Tudo on button click events and forward the object to the adapter so that the adapter class can then change the text in the layout

// * super.onCreate(savedInstanceState)
//This calls the parent class's onCreate method.
//It's essential for proper activity initialization and lifecycle management.
// * binding = ActivityMainBinding.inflate(layoutInflater)
//This creates the binding object for the activity's layout.
//ActivityMainBinding is an auto-generated class based on activity_main.xml.
//inflate(layoutInflater) inflates the XML layout into a view hierarchy.
// * setContentView(binding.root)
//This sets the content view of the activity to the root view of the inflated layout.
//binding.root is the top-level view in your layout.
//This makes your UI visible to the user.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adapter = TodoAdapter(mutableListOf())

//  What is the adapter? In Android, an adapter acts as a bridge between a data source (like a list of to-do items) and a view that displays that data (like a RecyclerView).
        binding.list.adapter = adapter

        binding.list.layoutManager = LinearLayoutManager(this)

        binding.addBtn.setOnClickListener{
           var textInBox = binding.todoText.text.toString()
            if(textInBox.isNotEmpty()){
                var todoObj:Todo = Todo(textInBox)
                adapter.addItem(todoObj)
                binding.todoText.text.clear()
            }

        }

        binding.deleteBtn.setOnClickListener{
            adapter.deleteItem()
        }

    }

}

