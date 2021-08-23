package sd.lemon.taskes.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sd.lemon.domain.taskes.models.Task
import sd.lemon.taskes.R

class TaskListAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_SHIMMER = 2
    }

    private val items = mutableListOf<Task?>()

    lateinit var action: OnActionsListener

    fun setOnActionsListener(listener: OnActionsListener) {
        this.action = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //TODO update as Shimmer view
        /** return if (viewType == VIEW_TYPE_ITEM){
        TaskViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.task_list, parent, false))
        }else{
        ShimmerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shimmer_list, parent, false))
        }*/
        return TaskViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.task_list, parent, false))
    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        //TODO Shimmer view
        if (viewHolder is TaskViewHolder) {
            val item = items[position]!!
            viewHolder.textViewTaskTitle.text = item.title
            viewHolder.textViewTaskBody.text = item.body
            viewHolder.itemView.setOnClickListener {
                action.onClick(items[position]!!)
            }
            viewHolder.itemView.setOnLongClickListener {
                action.onLongClick(items[position]!!)

                return@setOnLongClickListener true
            }
        }
    }

    fun showLoading() {
        items.addAll(listOf(null, null, null, null))
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null)
            VIEW_TYPE_SHIMMER
        else
            VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(data: List<Task>){
        this.items.clear()
        notifyDataSetChanged()
        this.items.addAll(data)
        notifyDataSetChanged()
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTaskTitle: TextView = view.findViewById(R.id.textViewTaskTitle)
        val textViewTaskBody: TextView = view.findViewById(R.id.textViewTaskBody)
    }

    class ShimmerViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnActionsListener {
        fun onClick(task: Task)
        fun onLongClick(task: Task)
    }


}