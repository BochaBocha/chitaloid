package reading.speed.improver.user.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import reading.speed.improver.R
import reading.speed.improver.user.pupil.management.PupilsManagementActivity

class TeacherMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_main_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val signInButton = findViewById<Button>(R.id.manage_accounts_btn)
        signInButton.setOnClickListener { startUsersManagement() }
    }

    private fun startUsersManagement() {
        val intent = Intent(this, PupilsManagementActivity::class.java)
        startActivity(intent)
    }
}
