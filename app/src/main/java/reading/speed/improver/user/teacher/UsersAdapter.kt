package reading.speed.improver.user.teacher

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import reading.speed.improver.utils.UsersDataHandler
import org.jetbrains.anko.dip
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView
import reading.speed.improver.repository.ChitaloidRepository
import java.util.*

class UsersAdapter(var list: ArrayList<String> = ArrayList<String>()) : BaseAdapter() {
    override fun getView(i : Int, v : View?, parent : ViewGroup?) : View {
        return with(parent!!.context) {
            var taskNum: Int = i +1

            linearLayout {
                textView{
                    text=list.get(i)
                    textSize = 16f
                    padding =dip(10)
                }.lparams{
                    height = dip(50)
                }
            }
        }
    }

    override fun getItem(position : Int) : String {
        return list[position]
    }

    override fun getCount() : Int {
        return list.size
    }

    override fun getItemId(position : Int) : Long {
        return 0L
    }

    fun add(text: String) {
        ChitaloidRepository.getInstance().addPupil(text)
        //UsersDataHandler.addUser(text)
        list.add(list.size, text)
        notifyDataSetChanged()
    }

    fun delete(i:Int) {
        // todo: fix it
       // UsersDataHandler.deleteUser(list.elementAt(i))
        list.removeAt(i)
        notifyDataSetChanged()
    }

}