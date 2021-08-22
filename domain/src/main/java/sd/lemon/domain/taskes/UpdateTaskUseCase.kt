package sd.lemon.domain.taskes

import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.models.Task

class UpdateTaskUseCase(private val repository: TasksRepository) :
    UseCase<UpdateTaskUseCase.Parameters, Task> {
    override fun execute(parameters: Parameters): Observable<Task> {
        return repository.updateTask(parameters)
    }

    class Parameters(val title: String, val body: String, val completed: Boolean, val id: Int) :
        UseCase.Parameters
}