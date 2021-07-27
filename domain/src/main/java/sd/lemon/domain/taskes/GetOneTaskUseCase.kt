package sd.lemon.domain.taskes

import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.modules.TasksModules

class GetOneTaskUseCase(private val repository: TasksRepository) :
    UseCase<GetOneTaskUseCase.Parameters, TasksModules> {
    class Parameters(val id: Int) : UseCase.Parameters

    override fun execute(parameters: Parameters): Observable<TasksModules> {
        return repository.getOneTask(parameters)
    }

}
