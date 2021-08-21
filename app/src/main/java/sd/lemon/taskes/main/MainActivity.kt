package sd.lemon.taskes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import sd.lemon.domain.taskes.models.Task
import sd.lemon.taskes.R
import sd.lemon.taskes.newTask.TaskFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerTaskAdapter: TaskListAdapter
    private lateinit var floatingActionButton: FloatingActionButton

    private val fragment = TaskFragment()

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        presenter.getTask()
        floatingActionButton.setOnClickListener {
            presenter.showDialog()
        }

    }

    override fun getTasks(taskList: List<Task>) {
        recyclerTaskAdapter = TaskListAdapter(taskList)
    }

    override fun getError(throwable: Throwable) {
        Snackbar.make(findViewById(android.R.id.content),
            "Sorry Something is Wring ≧ ﹏ ≦ \n Error message:$throwable",
            Snackbar.LENGTH_LONG).show()
    }

    override fun addTask() {
        TODO("Not yet implemented")
    }

    override fun taskFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()
    }
}