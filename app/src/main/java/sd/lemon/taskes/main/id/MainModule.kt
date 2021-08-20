package sd.lemon.taskes.main.id

import dagger.Module
import dagger.Provides
import sd.lemon.domain.taskes.GetTasksUseCase
import sd.lemon.domain.taskes.TasksRepository
import sd.lemon.taskes.app.id.PerActivity
import sd.lemon.taskes.main.MainPresenter
import sd.lemon.taskes.main.MainView

@Module
class MainModule(private val view: MainView) {

    @Provides
    @PerActivity
    fun provideGetTasksUseCase(tasksRepository: TasksRepository): GetTasksUseCase =
        GetTasksUseCase(tasksRepository)

    @Provides
    @PerActivity
    fun provideMainPresenter(getTasks: GetTasksUseCase): MainPresenter =
        MainPresenter(view, getTasks)

}