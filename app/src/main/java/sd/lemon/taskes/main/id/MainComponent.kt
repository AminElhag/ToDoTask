package sd.lemon.taskes.main.id

import dagger.Component
import sd.lemon.taskes.app.id.AppComponent
import sd.lemon.taskes.app.id.PerActivity
import sd.lemon.taskes.main.MainActivity

@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
@PerActivity
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}