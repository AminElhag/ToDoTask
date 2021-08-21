package sd.lemon.taskes.newTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import sd.lemon.taskes.R


class TaskFragment : Fragment(), TaskView {

    lateinit var presenter: TaskPresenter
    private lateinit var listener: OnActionsListener

    fun setOnActionsListener(actions: OnActionsListener) {
        this.listener = actions
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        TODO("Not yet implemented")
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

    interface OnActionsListener {
        fun actionHappen(index: Int)
    }
}