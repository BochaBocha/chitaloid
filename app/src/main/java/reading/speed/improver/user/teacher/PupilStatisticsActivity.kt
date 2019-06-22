package reading.speed.improver.user.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import reading.speed.improver.R
import reading.speed.improver.service.ChitaloidService

class PupilStatisticsActivity : AppCompatActivity() {
    lateinit var statisticsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistic_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        statisticsTextView = findViewById(R.id.statistics_textView)
        val pupilId = intent.getIntExtra("PupilId", 0)
        statisticsTextView.setText(getStatistic(pupilId))
    }

     fun getStatistic(pupilId: Int): String{
        var chitaloidService: ChitaloidService = ChitaloidService()
         return chitaloidService.getPupilStatistic(pupilId)
    }
}
