package sd.lemon.domain.taskes

import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.modules.TasksModules

class CreateTaskUseCase(private val repository: TasksRepository) :
    UseCase<CreateTaskUseCase.Parameters, TasksModules> {
    class Parameters(val id: Int, val title: String, val body: String, val completed: Boolean) :
        UseCase.Parameters

    override fun execute(parameters: Parameters): Observable<TasksModules> {
        return repository.createTask(parameters)
    }

}
