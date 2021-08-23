package sd.lemon.domain.taskes.models

import java.io.Serializable

data class Task(var id: Int, var title: String, var body: String, var completed: Boolean) : Serializable