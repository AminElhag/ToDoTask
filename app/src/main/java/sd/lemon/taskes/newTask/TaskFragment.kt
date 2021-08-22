package sd.lemon.taskes.newTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import sd.lemon.domain.taskes.models.Task
import sd.lemon.taskes.R
import javax.inject.Inject


class TaskFragment : Fragment(), TaskView {

    companion object {
        fun getTaskInstance(task: Task): TaskFragment {
            val fragment = TaskFragment()
            val bundle = Bundle()

            bundle.putSerializable("task", task)
            fragment.arguments = bundle
            return fragment
        }

        fun getInstance(): TaskFragment {
            return TaskFragment()
        }
    }

    @Inject
    lateinit var presenter: TaskPresenter
    //TODO("Inject")

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


        if (arguments?.containsKey("x") == true) {
            val serializableTask = requireArguments().getSerializable("") as Task
            titleText.setText(serializableTask.title)
            bodyText.setText(serializableTask.body)

            completeEditButton.setOnClickListener {
                presenter.updateTask(serializableTask)
            }
        }

        completeEditButton.setOnClickListener {
            presenter.addTask(titleText.text.toString(), bodyText.text.toString())

        }
    }

    override fun addTask() {
        view?.let {
            Snackbar.make(it.findViewById(android.R.id.content),
                "You add new task Good Luke o(≧∀≦)o",
                Snackbar.LENGTH_LONG).show()
            exit()
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
            exit()
        }
    }

    override fun exit() {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(this)
        fragmentTransaction.commit()
    }

    override fun taskUpdated(task: Task) {
        view?.let {
            Snackbar.make(it.findViewById(android.R.id.content),
                "You update your task status o(≧∀≦)o",
                Snackbar.LENGTH_LONG).show()
        }
        exit()
    }

}