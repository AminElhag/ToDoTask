package sd.lemon.domain.taskes

import io.reactivex.Completable

class DeleteTask(private val repository: TasksRepository) {
     fun execute(id: Int): Completable {
        return repository.deleteTask(id)
    }
}