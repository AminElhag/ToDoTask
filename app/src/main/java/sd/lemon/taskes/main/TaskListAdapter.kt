package sd.lemon.taskes.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sd.lemon.domain.taskes.TasksModules
import sd.lemon.taskes.R

class TaskListAdapter(private val taskModules: List<TasksModules>) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    lateinit var action: OnActionsListener

    fun setOnActionsListener(listener: OnActionsListener) {
        this.action = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTaskTitle: TextView = view.findViewById(R.id.textViewTaskTitle)
        val textViewTaskBody: TextView = view.findViewById(R.id.textViewTaskBody)
        val imageButtonTask: ImageButton = view.findViewById(R.id.imageButtonTask)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapter =
            LayoutInflater.from(parent.context).inflate(R.layout.task_list, parent, false)

        return ViewHolder(adapter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = taskModules[position]
        holder.textViewTaskTitle.text = item.title
        holder.textViewTaskBody.text = item.body

        if (item.completed) {
            holder.imageButtonTask.setImageResource(R.drawable.ic_baseline_mood_24)
        } else {
            holder.imageButtonTask.setImageResource(R.drawable.ic_baseline_mood_bad_24)
        }
    }

    override fun getItemCount(): Int {
        return taskModules.size
    }

    interface OnActionsListener {
        fun onClick(task: TasksModules)
    }

}