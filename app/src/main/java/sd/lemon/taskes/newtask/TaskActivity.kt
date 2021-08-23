package sd.lemon.taskes.newtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import sd.lemon.domain.taskes.models.Task
import sd.lemon.taskes.R
import sd.lemon.taskes.app.App
import sd.lemon.taskes.main.MainActivity
import sd.lemon.taskes.newtask.di.DaggerTaskComponent
import sd.lemon.taskes.newtask.di.TaskModule
import javax.inject.Inject

class TaskActivity : AppCompatActivity(), TaskView {

    @Inject
    lateinit var presenter: TaskPresenter

    private lateinit var titleText: EditText
    private lateinit var bodyText: EditText

    private lateinit var titleNewText: String
    private lateinit var bodyNewText: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        titleText = findViewById(R.id.editTextTaskTitle)
        bodyText = findViewById(R.id.editTextTaskBody)
        val completeEditButton =
            findViewById<FloatingActionButton>(R.id.floatingActionButtonTask)

        DaggerTaskComponent.builder()
            .appComponent((application as App).appComponent)
            .taskModule(TaskModule(this))
            .build()
            .inject(this)

        if (intent.hasExtra("task")) {
            val bundle = intent.getSerializableExtra("task") as Task
            titleText.setText(bundle.title)
            bodyText.setText(bundle.body)
            completeEditButton.setOnClickListener {
                titleNewText = titleText.text.toString()
                bodyNewText = bodyText.text.toString()

                presenter.updateTask(Task(bundle.id,
                    titleText.text.toString(),
                    bodyText.text.toString(),
                    true))
            }
        } else {
            completeEditButton.setOnClickListener {
                titleNewText = titleText.text.toString()
                bodyNewText = bodyText.text.toString()

                presenter.addTask(titleText.text.toString(), bodyText.text.toString())
            }
        }
    }

    override fun addTask() {
        Snackbar.make(findViewById(android.R.id.content),
            "You add new task Good Luke o(≧∀≦)o",
            Snackbar.LENGTH_LONG).show()

        titleText.setText(titleNewText)
        bodyText.setText(bodyNewText)


        //exit()
    }

    override fun getError(throwable: Throwable) {
        Snackbar.make(findViewById(android.R.id.content),
            "Sorry Something is Wring ≧ ﹏ ≦ \n Error message:$throwable",
            Snackbar.LENGTH_LONG).show()
    }

    override fun empty() {
        Snackbar.make(findViewById(android.R.id.content),
            "Can't create a empty taskヾ(￣▽￣) Bye~Bye~",
            Snackbar.LENGTH_LONG).show()
        //exit()
    }

    override fun exit() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    override fun taskUpdated(task: Task) {
        Snackbar.make(findViewById(android.R.id.content),
            "You update your task status o(≧∀≦)o",
            Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
