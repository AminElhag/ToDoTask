package sd.lemon.taskes.newTask.di

import dagger.Component
import sd.lemon.taskes.app.id.AppComponent
import sd.lemon.taskes.app.id.PerActivity
import sd.lemon.taskes.newTask.TaskFragment

@Component(modules = [TaskModule::class], dependencies = [AppComponent::class])
@PerActivity
interface TaskComponent {
    fun inject(taskFragment: TaskFragment)
}