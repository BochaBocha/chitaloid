package reading.speed.improver.user.pupil.statistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import reading.speed.improver.R
import reading.speed.improver.service.ChitaloidService

class PupilStatisticsActivity : AppCompatActivity() {
    private lateinit var statisticsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistic_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        statisticsTextView = findViewById(R.id.statistics_textView)
        statisticsTextView.text = getStatistic()
    }

     private fun getStatistic(): String{
        val chitaloidService = ChitaloidService()
         return chitaloidService.getPupilStatistic(chitaloidService.currentPupil._id)
    }
}
