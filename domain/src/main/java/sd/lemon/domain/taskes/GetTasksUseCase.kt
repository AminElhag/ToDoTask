package sd.lemon.domain.taskes

import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.models.Task

class GetTasksUseCase(private val repository: TasksRepository) :
    UseCase<GetTasksUseCase.Parameters, List<Task>> {

    override fun execute(parameters: Parameters): Observable<List<Task>> {
        return repository.getTasks(parameters)
    }

    class Parameters : UseCase.Parameters

}
