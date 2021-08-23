package sd.lemon.data.taskes

import io.reactivex.Completable
import io.reactivex.Observable
import sd.lemon.domain.taskes.*
import sd.lemon.domain.taskes.models.Task

class MemoryImp : TasksRepository {

    private var tasks: MutableList<Task> = mutableListOf()
    private var id: Int = 0


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
        val index = tasks.indexOfFirst { it.id == id }
        return if (index > -1) {
            tasks.removeAt(index)
            Completable.complete()
        } else {
            Completable.error(Throwable("Item not found"))
        }
    }

    override fun updateTask(parameters: UpdateTaskUseCase.Parameters): Observable<Task> {
        val task = tasks.firstOrNull { it.id == id }
        if (task != null){
            task.apply {
                title = parameters.title
                body = parameters.body
                completed = parameters.completed
            }

            tasks[tasks.indexOfFirst { it.id == parameters.id }] = task
            return Observable.just(task)
        }else{
            return Observable.error(Throwable("item not found"))
        }
    }
}