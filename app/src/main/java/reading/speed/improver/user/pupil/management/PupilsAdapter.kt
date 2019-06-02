package reading.speed.improver.user.pupil.management

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.jetbrains.anko.dip
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import java.util.*

class PupilsAdapter(var list: ArrayList<Pupil> = ArrayList()) : BaseAdapter() {
    override fun getView(i: Int, v: View?, parent: ViewGroup?): View {
        return with(parent!!.context) {
            var taskNum: Int = i + 1
            linearLayout {
                textView {
                    text = list.get(i).name
                    textSize = 16f
                    padding = dip(10)
                }.lparams {
                    height = dip(50)
                }
            }
        }
    }

    override fun getItem(position: Int): Pupil {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    fun getPupil(position: Int): Pupil {
        return list[position]
    }

    fun add(text: String) {
        val chitaloidService: ChitaloidService = ChitaloidService()
        var pupil: Pupil = chitaloidService.createPupil(text)
        chitaloidService.addPupil(pupil)
        pupil = chitaloidService.getPupilByName(pupil.name)
        list.add(list.size, pupil)
        notifyDataSetChanged()
    }

    fun delete(i: Int) {
        ChitaloidRepository.getInstance().deletePupil(list[i])
        list.removeAt(i)
        notifyDataSetChanged()
    }

}