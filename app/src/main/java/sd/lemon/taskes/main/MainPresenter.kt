package sd.lemon.taskes.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sd.lemon.domain.taskes.CreateTaskUseCase
import sd.lemon.domain.taskes.GetTasksUseCase

class MainPresenter(
    private val view: MainView,
    private val getTasksUseCase: GetTasksUseCase,
    private val addTasksUseCase: CreateTaskUseCase,
) {

    private val compositeDisposable = CompositeDisposable()

    fun getTask() {
        val subscribe = getTasksUseCase.execute(GetTasksUseCase.Parameters())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                view.getTasks(it)
            }, {
                view.getError(it)
            })

        compositeDisposable.add(subscribe)
    }

    fun addTask(title: String, body: String) {
        val subscribe =
            addTasksUseCase.execute(CreateTaskUseCase.Parameters(title, body, completed = false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    view.addTask()
                }, {
                    view.getError(it)
                })
    }

    fun showDialog() {
        view.taskDialog()
    }
}