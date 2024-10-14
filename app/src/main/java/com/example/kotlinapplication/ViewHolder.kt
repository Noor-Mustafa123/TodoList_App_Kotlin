package com.example.kotlinapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
//The Adapter receives data from a data source (e.g., a list of to-do items).
//It creates ViewHolder objects, which hold the views for each item.
//The Adapter binds the data to the views within the ViewHolder.
//The RecyclerView displays the ViewHolder objects to the user.
//As the user scrolls, the RecyclerView recycles and reuses ViewHolder objects for efficiency.
class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)