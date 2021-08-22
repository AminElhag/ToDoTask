package sd.lemon.taskes.newTask

import sd.lemon.domain.taskes.models.Task

interface TaskView {
    fun addTask()
    fun getError(throwable: Throwable)
    fun empty()
    fun exit()
    fun taskUpdated(task: Task)
}