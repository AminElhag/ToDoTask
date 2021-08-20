package sd.lemon.taskes.app.id

import dagger.Component
import sd.lemon.taskes.app.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
}