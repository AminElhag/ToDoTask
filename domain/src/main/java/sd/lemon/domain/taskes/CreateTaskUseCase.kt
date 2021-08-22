package sd.lemon.domain.taskes

import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.models.Task

class CreateTaskUseCase(private val repository: TasksRepository) :
    UseCase<CreateTaskUseCase.Parameters, Task> {
    class Parameters(val title: String, val body: String, val completed: Boolean=false) :
        UseCase.Parameters

    override fun execute(parameters: Parameters): Observable<Task> {
        return repository.createTask(parameters)
    }

}
