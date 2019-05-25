package reading.speed.improver.user.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.setContentView
import reading.speed.improver.user.pupil.management.PupilsManagement

class TeacherMainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TeacherUI().setContentView(this)
    }
    fun startUsersManagement() {
        val intent = Intent(this, PupilsManagement::class.java)
        startActivity(intent)
    }
}
