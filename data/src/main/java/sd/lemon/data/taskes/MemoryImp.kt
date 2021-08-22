package sd.lemon.data.taskes

import io.reactivex.Completable
import io.reactivex.Observable
import sd.lemon.domain.taskes.*
import sd.lemon.domain.taskes.models.Task

class MemoryImp : TasksRepository {

    var tasks: MutableList<Task> = mutableListOf()
    var id: Int = 0


    override fun getTasks(parameters: GetTasksUseCase.Parameters): Observable<List<Task>> {
        return Observable.just(tasks)
    }

    override fun getOneTask(parameters: GetTaskByIdUseCase.Parameters): Observable<Task> {
        return Observable.just(tasks.first { it.id == parameters.id })
    }

    override fun createTask(parameters: CreateTaskUseCase.Parameters): Observable<Task> {
        id += 1
        val task = Task(id, parameters.title, parameters.body, parameters.completed)
        tasks.add(task)
        return Observable.just(task)
    }

    override fun deleteTask(id: Int): Completable {
        tasks.removeAt(id)
        return Completable.complete()
    }

    override fun updateTask(parameters: UpdateTaskUseCase.Parameters): Observable<Task> {
        tasks[parameters.id] = Task(title = parameters.title,
            body = parameters.body,
            completed = parameters.completed,
            id = parameters.id
        )
        return Observable.just(tasks[parameters.id])
    }
}