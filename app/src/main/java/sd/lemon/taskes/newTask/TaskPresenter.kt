package sd.lemon.taskes.newTask

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sd.lemon.domain.taskes.CreateTaskUseCase

class TaskPresenter(
    private val view: TaskView,
    private val createTaskUseCase: CreateTaskUseCase,
) {
    private val compositeDisposable = CompositeDisposable()

    fun dataIsComplete(title: String, body: String): Boolean {
        if (title.isEmpty() || body.isEmpty()) {
            return false
        }
        return true
    }

    fun addTask(title: String, body: String) {
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
}