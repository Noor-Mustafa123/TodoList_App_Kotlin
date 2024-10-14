package com.example.kotlinapplication
//. Data classes in Kotlin are specialized classes primarily used to hold data. They automatically generate several useful functions like equals(), hashCode(), toString(), and copy(), reducing boilerplate code. 2. Constructor and Properties
data class Todo (
    val description : String,
//    by default the value is kept false
    var isChecked : Boolean = false
)