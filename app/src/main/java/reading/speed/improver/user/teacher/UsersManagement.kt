package reading.speed.improver.user.teacher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import reading.speed.improver.utils.UsersDataHandler
import org.jetbrains.anko.setContentView
import java.util.*

class UsersManagement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var users = ArrayList(UsersDataHandler.getAllUsers())
        var adapter= UsersAdapter(users)
        var ui = UsersManagementUI(adapter)
        ui.setContentView(this)
    }
}