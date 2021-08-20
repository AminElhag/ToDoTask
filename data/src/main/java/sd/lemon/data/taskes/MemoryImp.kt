package sd.lemon.data.taskes

import io.reactivex.Completable
import io.reactivex.Observable
import sd.lemon.domain.taskes.CreateTaskUseCase
import sd.lemon.domain.taskes.GetTaskByIdUseCase
import sd.lemon.domain.taskes.GetTasksUseCase
import sd.lemon.domain.taskes.TasksRepository
import sd.lemon.domain.taskes.models.Task

class MemoryImp : TasksRepository {

    var tasks: MutableList<Task> = mutableListOf()
    var id: Int = 0

    override fun getTasks(parameters: GetTasksUseCase.Parameters): Observable<List<Task>> {
        return Observable.just(tasks)
    }

    override fun getOneTask(parameters: GetTaskByIdUseCase.Parameters): Observable<Task> {
        return Observable.just(tasks[parameters.id])
    }

    override fun createTask(parameters: CreateTaskUseCase.Parameters): Observable<Task> {
        id += 1
        tasks.add(Task(id, parameters.title, parameters.body, parameters.completed))
        return Observable.just(tasks[id])
    }

    override fun deleteTask(id: Int): Completable {
        tasks.removeAt(id)
        return Completable.complete()
    }
}