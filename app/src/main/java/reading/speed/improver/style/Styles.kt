package reading.speed.improver.style

import android.view.Gravity
import android.widget.Button
import org.jetbrains.anko.*

val DefaultStyle = { v: Any ->
    when (v) {
        is Button -> with(v) {
            gravity = Gravity.CENTER_HORIZONTAL
            padding = dip(20)
        }
    }
}
