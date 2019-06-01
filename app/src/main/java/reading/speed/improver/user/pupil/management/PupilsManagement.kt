package reading.speed.improver.user.pupil.management

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.teacher.PupilStatisticsActivity
import java.util.*

class PupilsManagement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pupils = ChitaloidRepository.getInstance().pupils
        var adapter= PupilsAdapter(ArrayList(pupils))
        var ui = PupilsManagementUI(adapter)
        ui.setContentView(this)
    }
    fun startStatisticsScreen(pupil: Pupil) {
        val intent = Intent(this, PupilStatisticsActivity::class.java)
        intent.putExtra("PupilId", pupil._id)
        startActivity(intent)
    }
    fun deleteStatistics(pupil: Pupil) {
       val chitaloidService: ChitaloidService = ChitaloidService()
        chitaloidService.deleteStatistics(pupil)
    }
}