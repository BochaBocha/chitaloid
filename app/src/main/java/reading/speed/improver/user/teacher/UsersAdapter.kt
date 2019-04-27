package reading.speed.improver.user.teacher

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.jetbrains.anko.dip
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import java.util.*

class UsersAdapter(var list: ArrayList<Pupil> = ArrayList<Pupil>()) : BaseAdapter() {
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

    fun add(text: String) {
        val chitaloidService: ChitaloidService = ChitaloidService()
        val pupil: Pupil = chitaloidService.createPupil(text)
        chitaloidService.addPupil(pupil)
        list.add(list.size, pupil)
        notifyDataSetChanged()
    }

    fun delete(i: Int) {
        // todo: fix it
        // UsersDataHandler.deleteUser(list.elementAt(i))
        list.removeAt(i)
        notifyDataSetChanged()
    }

}