package com.example.trofi.course_work.auth

import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.example.trofi.course_work.teacher.UsersAdapter

class SignInUI(val usersAdapter: UsersAdapter) : AnkoComponent<SignIn> {
    lateinit var hintListView: TextView

    override fun createView(ui: AnkoContext<SignIn>): View = with(ui) {

        return relativeLayout {
            var usersList: ListView? = null

             hintListView = textView("Нет зарегистрированных пользователей") {
                textSize = 20f
                 visibility = View.GONE
            }.lparams {
                centerInParent()
            }


            verticalLayout {
                usersList = listView {
                    adapter = usersAdapter
                    onItemClick { _, view, i, l ->
                        var user = adapter.getItem(i)
                        alert("Выбрать пользователя ${user}?", "Подтверждение"){
                            yesButton{owner.completeSignIn(adapter.getItem(i).toString())}
                            noButton{}
                        }.show()
                    }
                }
            }.lparams {
                margin = dip(5)
            }
            showHideHintListView(usersList!!)
        }

    }

    fun showHideHintListView(listView: ListView) {
        if (getTotalListItems(listView) > 0) {
            hintListView.visibility = View.GONE
        } else {
            hintListView.visibility = View.VISIBLE
        }
    }
    fun getTotalListItems(list: ListView?) = list?.adapter?.count ?: 0
}