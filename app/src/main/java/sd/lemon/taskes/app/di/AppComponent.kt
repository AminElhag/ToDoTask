package sd.lemon.taskes.app.di

import dagger.Component
import sd.lemon.data.taskes.MemoryImp
import sd.lemon.domain.taskes.TasksRepository
import sd.lemon.taskes.app.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, StorageModule::class])
interface AppComponent {
    fun inject(app: App)

    fun getMemoryImp() : TasksRepository
}