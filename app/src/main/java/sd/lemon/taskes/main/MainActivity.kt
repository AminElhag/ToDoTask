package sd.lemon.taskes.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import sd.lemon.domain.taskes.models.Task
import sd.lemon.taskes.R
import sd.lemon.taskes.app.App
import sd.lemon.taskes.main.di.DaggerMainComponent
import sd.lemon.taskes.main.di.MainModule
import sd.lemon.taskes.newtask.TaskActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var recyclerView: RecyclerView
    private val recyclerTaskAdapter: TaskListAdapter = TaskListAdapter()
    private lateinit var floatingActionButton: FloatingActionButton


    @Inject
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        DaggerMainComponent
            .builder()
            .appComponent((application as App).appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)


        initRecycler()
        floatingActionButton.setOnClickListener {
            startActivity(Intent(applicationContext, TaskActivity::class.java))
        }

    }

    private fun initRecycler() {
        recyclerTaskAdapter.setOnActionsListener(object : TaskListAdapter.OnActionsListener {
            override fun onClick(task: Task) {
                startActivity(Intent(applicationContext, TaskActivity::class.java).apply {
                    putExtra("task", task)
                })
            }

            override fun onLongClick(task: Task) {
                presenter.deleteTask(task)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerTaskAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.getTask()

    }

    override fun getTasks(taskList: List<Task>) {
        recyclerTaskAdapter.setData(taskList)
    }

    override fun getError(throwable: Throwable) {
        Snackbar.make(findViewById(android.R.id.content),
            "Sorry Something is Wring ≧ ﹏ ≦ \n Error message:$throwable",
            Snackbar.LENGTH_LONG).show()
    }

    override fun addTask() {
        TODO("Not yet implemented")
    }

    override fun deleteTask() {
        Snackbar.make(findViewById(android.R.id.content),
            "Task delete ╯︿╰",
            Snackbar.LENGTH_LONG).show()

        presenter.getTask()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}