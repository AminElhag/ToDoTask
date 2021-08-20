package sd.lemon.taskes.app

import android.app.Application
import sd.lemon.taskes.app.id.AppComponent
import sd.lemon.taskes.app.id.DaggerAppComponent
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .build()
            .inject(this)
    }
}