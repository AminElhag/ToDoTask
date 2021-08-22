package sd.lemon.domain.taskes

import io.reactivex.Completable
import io.reactivex.Observable
import sd.lemon.domain.taskes.models.Task

interface TasksRepository {
    fun getTasks(parameters: GetTasksUseCase.Parameters): Observable<List<Task>>
    fun getOneTask(parameters: GetTaskByIdUseCase.Parameters): Observable<Task>
    fun createTask(parameters: CreateTaskUseCase.Parameters): Observable<Task>
    fun deleteTask(id:Int): Completable
    fun updateTask(parameters:UpdateTaskUseCase.Parameters):Observable<Task>
}