package sd.lemon.taskes.main.id

import dagger.Module
import dagger.Provides
import sd.lemon.data.taskes.MemoryImp
import sd.lemon.domain.taskes.CreateTaskUseCase
import sd.lemon.domain.taskes.GetTasksUseCase
import sd.lemon.domain.taskes.TasksRepository
import sd.lemon.taskes.app.id.PerActivity
import sd.lemon.taskes.main.MainPresenter
import sd.lemon.taskes.main.MainView
import javax.inject.Singleton

@Module
class MainModule(private val view: MainView) {

    @Provides
    @PerActivity
    fun provideTasksRepository(): TasksRepository =
        MemoryImp()


    @Provides
    @PerActivity
    fun provideCreateTaskUseCase(tasksRepository: TasksRepository): CreateTaskUseCase =
        CreateTaskUseCase(tasksRepository)

    @Provides
    @PerActivity
    fun provideGetTasksUseCase(tasksRepository: TasksRepository): GetTasksUseCase =
        GetTasksUseCase(tasksRepository)

    @Provides
    @PerActivity
    fun provideMainPresenter(
        getTasksUseCase: GetTasksUseCase,
        createTaskUseCase: CreateTaskUseCase,
    ): MainPresenter =
        MainPresenter(view, getTasksUseCase, createTaskUseCase)

}