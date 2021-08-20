package sd.lemon.domain.taskes.models

import java.io.Serializable

data class Task(val id: Int, val title: String, val body: String, val completed: Boolean) : Serializable