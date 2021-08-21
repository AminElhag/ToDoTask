package sd.lemon.taskes.newTask

interface TaskView {
    fun addTask()
    fun getError(throwable: Throwable)
}