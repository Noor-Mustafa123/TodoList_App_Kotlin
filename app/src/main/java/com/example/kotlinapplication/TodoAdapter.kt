import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.Todo
import com.example.kotlinapplication.ViewHolder
import com.example.kotlinapplication.databinding.TodoItemBinding

class TodoAdapter(var todosList: MutableList<Todo>) : RecyclerView.Adapter<ViewHolder>(){
   public lateinit var itemTodoBinding: TodoItemBinding;


    fun addItem(todo: Todo){
        todosList.add(todo);
//        Notify any registered observers that the item reflected at position has been newly inserted. The item previously at position is now at position position + 1.
        notifyItemInserted(todosList.size-1)
    }

    fun deleteItem(){
        todosList.removeAll { todo -> todo.isChecked };
        notifyDataSetChanged();
// While primarily known for its observer-related functionality, notifyDataSetChanged() has a distinct role within RecyclerView adapters. When called, it forces the adapter to completely refresh and re-render all the items in the RecyclerView.
//Invalidation: notifyDataSetChanged() signals the RecyclerView that the underlying data has changed significantly. This invalidates the cached view holders and layouts for all items.
//Rebinding: The RecyclerView will then call the adapter's onBindViewHolder() method for each item to rebind the data to the views, updating the displayed content.
//Layout Update: If necessary, the RecyclerView will also recalculate the layout of the items to accommodate any changes in data size or structure.
    }

    fun toggleStrikeThrough(item: TodoItemBinding ,isChecked: Boolean ){
//  The or operator is used to add the STRIKE_THRU_TEXT_FLAG to the existing paintFlags of the tvTodoTitle TextView without removing any other flags that might be set.
//This ensures that the strikethrough effect is applied while maintaining other text rendering properties.
//Why not just assign STRIKE_THRU_TEXT_FLAG directly?
//If you directly assigned tvTodoTitle.paintFlags = STRIKE_THRU_TEXT_FLAG, you would be overwriting any other flags that were previously set in paintFlags.
//This could lead to unintended consequences, like removing other text styling or rendering behaviors.
        if(isChecked){
            item.tvTodoTitle.paintFlags = item.tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            item.tvTodoTitle.paintFlags = item.tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


//This function is called by the RecyclerView to display the data for a particular item at a given position in the list.
//It's responsible for taking the data for an item and binding it to the views within the corresponding view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val curTodo = todosList[position]
            holder.itemView.apply {
                itemTodoBinding.tvTodoTitle.text = curTodo.description
                itemTodoBinding.cbDone.isChecked = curTodo.isChecked
                toggleStrikeThrough(itemTodoBinding, curTodo.isChecked)
                itemTodoBinding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(itemTodoBinding, isChecked)
                // on click the current checked condition is inverted
                    curTodo.isChecked = !curTodo.isChecked
                }
            }
        }
// Retrieves the data for an item at a specific position.
//Binds that data to the views within the item's view holder (setting the title text, checkbox state, and applying/removing the strikethrough effect).
//Sets a listener to handle changes in the checkbox state, updating both the visual representation (strikethrough) and the underlying data model (curTodo.isChecked).



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        In onCreateViewHolder(), parent refers to the RecyclerView itself.
//        By using parent.context, you are getting the RecyclerView's Context, which is essential for tasks
        itemTodoBinding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false);
//        we are providing the view holder with inflated view objects
            return ViewHolder(itemTodoBinding.root)
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

}