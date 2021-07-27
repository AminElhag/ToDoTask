package sd.lemon.domain.taskes

import io.reactivex.Completable
import io.reactivex.Observable
import sd.lemon.domain.common.UseCase
import sd.lemon.domain.taskes.modules.TasksModules

class DeleteTask(private val repository: TasksRepository) {
     fun execute(id: Int): Completable {
        return repository.deleteTask(id)
    }
}