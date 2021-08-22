package sd.lemon.taskes.main

import sd.lemon.domain.taskes.models.Task

interface MainView {
    fun getTasks(taskList: List<Task>)
    fun getError(throwable: Throwable)
    fun addTask()
    fun taskFragment()
    fun taskFragment(task: Task)
    fun deleteTask()
}