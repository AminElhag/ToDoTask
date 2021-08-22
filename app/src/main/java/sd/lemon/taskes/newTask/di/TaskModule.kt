package sd.lemon.taskes.newTask.di

import dagger.Module
import dagger.Provides
import sd.lemon.data.taskes.MemoryImp
import sd.lemon.domain.taskes.CreateTaskUseCase
import sd.lemon.domain.taskes.TasksRepository
import sd.lemon.domain.taskes.UpdateTaskUseCase
import sd.lemon.taskes.app.id.PerActivity
import sd.lemon.taskes.newTask.TaskPresenter
import sd.lemon.taskes.newTask.TaskView

@Module
class TaskModule(private val view: TaskView) {
    
    @Provides
    @PerActivity
    fun provideTasksRepository(): TasksRepository =
        MemoryImp()

    @Provides
    @PerActivity
    fun provideUpdateTaskUseCase(
        repository: TasksRepository,
    ): UpdateTaskUseCase =
        UpdateTaskUseCase(repository)

    @Provides
    @PerActivity
    fun provideCreateTaskUseCase(
        repository: TasksRepository,
    ): CreateTaskUseCase = CreateTaskUseCase(repository)

    @Provides
    @PerActivity
    fun provideTaskPresenter(
        createTaskUseCase: CreateTaskUseCase,
        updateTaskUseCase: UpdateTaskUseCase,
    ): TaskPresenter = TaskPresenter(view, createTaskUseCase, updateTaskUseCase)
}