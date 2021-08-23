package sd.lemon.taskes.newtask.di

import dagger.Component
import sd.lemon.taskes.app.di.AppComponent
import sd.lemon.taskes.app.di.PerActivity
import sd.lemon.taskes.newtask.TaskActivity

@Component(modules = [TaskModule::class], dependencies = [AppComponent::class])
@PerActivity
interface TaskComponent {
    fun inject(taskActivity: TaskActivity)
}