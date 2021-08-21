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
}