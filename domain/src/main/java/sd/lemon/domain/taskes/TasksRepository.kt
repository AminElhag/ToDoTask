package sd.lemon.domain.taskes

import io.reactivex.Completable
import io.reactivex.Observable
import sd.lemon.domain.taskes.modules.TasksModules

interface TasksRepository {
    fun getTasks(parameters: GetTasksUseCase.Parameters): Observable<List<TasksModules>>
    fun getOneTask(parameters: GetOneTaskUseCase.Parameters): Observable<TasksModules>
    fun createTask(parameters: CreateTaskUseCase.Parameters): Observable<TasksModules>
    fun deleteTask(id:Int): Completable
}