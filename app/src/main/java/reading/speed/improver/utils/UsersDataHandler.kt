package com.example.trofi.course_work.utils

import android.os.Environment
import android.support.v7.app.AppCompatActivity
import com.example.trofi.course_work.exercises.Exercise1.Exercise1
import com.example.trofi.course_work.exercises.Exercise2.Exercise2
import com.example.trofi.course_work.exercises.Exercise3.Exercise3
import com.example.trofi.course_work.exercises.Exercise4.Exercise4
import java.io.*

object UsersDataHandler : AppCompatActivity() {
    val usersFilename: String = "available_users.txt"
    val exercises: HashMap<String, Class<*>> = hashMapOf(
            "Слова" to Exercise1::class.java,
            "Словосочетания" to Exercise2::class.java,
            "Слова на время" to Exercise3::class.java,
            "Перевернутый текст" to Exercise4::class.java
    )

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