package sd.lemon.taskes.newTask

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sd.lemon.domain.taskes.CreateTaskUseCase
import sd.lemon.domain.taskes.UpdateTaskUseCase
import sd.lemon.domain.taskes.models.Task

class TaskPresenter(
    private val view: TaskView,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) {
    private val compositeDisposable = CompositeDisposable()

    fun addTask(title: String, body: String) {
        if (title.isEmpty() || body.isEmpty()) {
            view.empty()
        }
        val subscribe =
            createTaskUseCase.execute(CreateTaskUseCase.Parameters(title, body, completed = false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    view.addTask()
                }, {
                    view.getError(it)
                })

        compositeDisposable.add(subscribe)
    }

    fun updateTask(task: Task) {
        val subscribe = updateTaskUseCase.execute(UpdateTaskUseCase.Parameters(task.title,
            task.body,
            task.completed,
            task.id)).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                view.taskUpdated(it)
            }, {
                view.getError(it)
            })

        compositeDisposable.add(subscribe)
    }
}