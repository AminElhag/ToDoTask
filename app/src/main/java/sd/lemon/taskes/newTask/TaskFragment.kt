package sd.lemon.taskes.newTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import sd.lemon.taskes.R


class TaskFragment : Fragment(), TaskView {

    lateinit var presenter: TaskPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleText = view.findViewById<EditText>(R.id.editTextTaskTitle)
        val bodyText = view.findViewById<EditText>(R.id.editTextTaskBody)
        val completeEditButton =
            view.findViewById<FloatingActionButton>(R.id.floatingActionButtonTask)

        completeEditButton.setOnClickListener {
            presenter.addTask(titleText.text.toString(), bodyText.text.toString())

        }
    }

    override fun addTask() {
        view?.let {
            Snackbar.make(it.findViewById(android.R.id.content),
                "You add new task Good Luke o(≧∀≦)o",
                Snackbar.LENGTH_LONG).show()
        }
    }

    override fun getError(throwable: Throwable) {
        view?.let {
            Snackbar.make(it.findViewById(android.R.id.content),
                "Sorry Something is Wring ≧ ﹏ ≦ \n Error message:$throwable",
                Snackbar.LENGTH_LONG).show()
        }
    }

    override fun empty() {
        view?.let {
            Snackbar.make(it.findViewById(android.R.id.content),
                "Can't create a empty taskヾ(￣▽￣) Bye~Bye~",
                Snackbar.LENGTH_LONG).show()
        }
    }

}