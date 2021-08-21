package sd.lemon.taskes.newTask.id

import dagger.Module
import dagger.Provides
import sd.lemon.domain.taskes.CreateTaskUseCase
import sd.lemon.domain.taskes.TasksRepository
import sd.lemon.taskes.app.id.PerActivity
import sd.lemon.taskes.newTask.TaskPresenter
import sd.lemon.taskes.newTask.TaskView

@Module
class TaskModule(private val view: TaskView) {

    @Provides
    @PerActivity
    fun provideCreateTaskUseCase(
        repository: TasksRepository,
    ): CreateTaskUseCase = CreateTaskUseCase(repository)

    @Provides
    @PerActivity
    fun provideTaskPresenter(
        createTaskUseCase: CreateTaskUseCase,
    ): TaskPresenter = TaskPresenter(view, createTaskUseCase)
}