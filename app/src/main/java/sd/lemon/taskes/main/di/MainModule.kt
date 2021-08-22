package sd.lemon.taskes.main.di

import dagger.Module
import dagger.Provides
import sd.lemon.data.taskes.MemoryImp
import sd.lemon.domain.taskes.CreateTaskUseCase
import sd.lemon.domain.taskes.DeleteTaskUseCase
import sd.lemon.domain.taskes.GetTasksUseCase
import sd.lemon.domain.taskes.TasksRepository
import sd.lemon.taskes.app.id.PerActivity
import sd.lemon.taskes.main.MainPresenter
import sd.lemon.taskes.main.MainView

@Module
class MainModule(private val view: MainView) {

    @Provides
    @PerActivity
    fun provideTasksRepository(): TasksRepository =
        MemoryImp()


    @Provides
    @PerActivity
    fun provideDeleteTaskUseCase(tasksRepository: TasksRepository): DeleteTaskUseCase =
        DeleteTaskUseCase(tasksRepository)


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
        deleteTaskUseCase: DeleteTaskUseCase,
    ): MainPresenter =
        MainPresenter(view, getTasksUseCase, createTaskUseCase, deleteTaskUseCase)

}