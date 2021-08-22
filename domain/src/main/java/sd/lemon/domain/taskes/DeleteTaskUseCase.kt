package sd.lemon.domain.taskes

import io.reactivex.Completable

class DeleteTaskUseCase(private val repository: TasksRepository) {
     fun execute(id: Int): Completable {
        return repository.deleteTask(id)
    }
}