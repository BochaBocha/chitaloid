package reading.speed.improver.user.teacher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import reading.speed.improver.utils.UsersDataHandler
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import java.util.*

class UsersManagement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var users = ArrayList(UsersDataHandler.getAllUsers())

        var pupils = ChitaloidRepository.getInstance().pupils

        var pupilsNames: ArrayList<String> = arrayListOf()
        for (pupil in pupils){
            pupilsNames.add(pupil.name)
        }
        var adapter= UsersAdapter(pupilsNames)
        var ui = UsersManagementUI(adapter)
        ui.setContentView(this)
    }
}