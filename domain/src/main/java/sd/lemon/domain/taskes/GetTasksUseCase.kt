package sd.lemon.domain.taskes

import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.modules.TasksModules

class GetTasksUseCase(private val repository: TasksRepository) :
    UseCase<GetTasksUseCase.Parameters, List<TasksModules>> {
    class Parameters : UseCase.Parameters

    override fun execute(parameters: Parameters): Observable<List<TasksModules>> {
        return repository.getTasks(parameters)
    }

}
