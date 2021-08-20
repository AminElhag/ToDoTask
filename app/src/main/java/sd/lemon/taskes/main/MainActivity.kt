package sd.lemon.taskes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import sd.lemon.domain.taskes.models.Task
import sd.lemon.taskes.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerTaskAdapter: TaskListAdapter

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        presenter.getTask()

    }

    override fun getTasks(taskList: List<Task>) {
        recyclerTaskAdapter = TaskListAdapter(taskList)
    }

    override fun getError(throwable: Throwable) {
        Snackbar.make(findViewById(android.R.id.content),
            "Sorry Something is Wring ≧ ﹏ ≦ \n Error message:$throwable",
            Snackbar.LENGTH_LONG).show()
    }
}

//TODO ID