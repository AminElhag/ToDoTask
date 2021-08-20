package sd.lemon.domain.taskes

import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.models.Task

class GetTaskByIdUseCase(private val repository: TasksRepository) :
    UseCase<GetTaskByIdUseCase.Parameters, Task> {
    class Parameters(val id: Int) : UseCase.Parameters

    override fun execute(parameters: Parameters): Observable<Task> {
        return repository.getOneTask(parameters)
    }

}
