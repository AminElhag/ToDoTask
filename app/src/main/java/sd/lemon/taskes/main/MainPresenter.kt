package sd.lemon.taskes.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sd.lemon.domain.taskes.GetTasksUseCase

class MainPresenter(
    private val view: MainView,
    private val getTasks: GetTasksUseCase,
) {

    private val compositeDisposable = CompositeDisposable()

    fun getTask() {
        val subscribe = getTasks.execute(GetTasksUseCase.Parameters())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                view.getTasks(it)
            }, {
                view.getError(it)
            })

        compositeDisposable.add(subscribe)
    }
}