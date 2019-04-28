package reading.speed.improver.utils

import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import reading.speed.improver.exercises.ui.ExerciseActivity

@Deprecated("Uses .txt file for storing pupils")
class PupilsDataHandler : AppCompatActivity() {
    val usersFilename: String = "available_users.txt"
    val exercises: HashMap<String, Class<*>> = hashMapOf(
            "Базовое упражнение" to ExerciseActivity::class.java
    )

    fun getListOfExercises(): HashMap<String, Class<*>>{
        return exercises
    }

    fun getAllUsers(): List<String> {
        var users = emptyList<String>().toMutableList()
        try {
            val file = File(Environment.getExternalStorageDirectory(), usersFilename)
            val fos = FileInputStream(file)
            val inputStreamReader = InputStreamReader(fos)
            inputStreamReader.forEachLine {
                users.add(it)
            }
            inputStreamReader.close()
        } catch (ex: Exception) {
            print(ex.message)
        }
        return users
    }

    fun isUserExists(name: CharSequence): Boolean {
        var found = false
        try {
            val file = File(Environment.getExternalStorageDirectory(), usersFilename)
            val fos = FileInputStream(file)
            val inputStreamReader = InputStreamReader(fos)
            inputStreamReader.forEachLine {
                if (it == name.toString()) {
                    found = true
                }
            }
            inputStreamReader.close()
        } catch (ex: Exception) {
            print(ex.message)
        }
        return found
    }

    fun addUser(name: CharSequence) {
        try {
            val file = File(Environment.getExternalStorageDirectory(), usersFilename)
            val fos = FileOutputStream(file, true)
            val outputStreamWriter = OutputStreamWriter(fos)
            outputStreamWriter.append(name.toString())
            outputStreamWriter.append("\n")
            outputStreamWriter.flush()
            outputStreamWriter.close()
        } catch (ex: Exception) {
            print(ex.message)
        }
    }

    fun deleteUser(name: String) {
        var users = getAllUsers()
        try {
            val file = File(Environment.getExternalStorageDirectory(), usersFilename)
            val fos = FileOutputStream(file)
            val outputStreamWriter = OutputStreamWriter(fos)
            users.forEach {
                if (it != name) {
                    outputStreamWriter.append(it)
                    outputStreamWriter.append("\n")
                }
            }
            outputStreamWriter.flush()
            outputStreamWriter.close()
        } catch (ex: Exception) {
            print(ex.message)
        }
    }

}