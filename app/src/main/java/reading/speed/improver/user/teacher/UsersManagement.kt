package reading.speed.improver.user.teacher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import java.util.*

class UsersManagement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var users = ArrayList(UsersDataHandler.getAllUsers())

        var pupils = ChitaloidRepository.getInstance().pupils
        var adapter= UsersAdapter(ArrayList(pupils))
        var ui = UsersManagementUI(adapter)
        ui.setContentView(this)
    }
}