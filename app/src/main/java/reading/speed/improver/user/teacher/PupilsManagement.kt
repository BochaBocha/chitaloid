package reading.speed.improver.user.teacher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import java.util.*

class PupilsManagement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pupils = ChitaloidRepository.getInstance().pupils
        var adapter= PupilsAdapter(ArrayList(pupils))
        var ui = PupilsManagementUI(adapter)
        ui.setContentView(this)
    }
}