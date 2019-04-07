package reading.speed.improver.utils

import android.app.Application

class ExercisesDataHandler {
    fun getWordsFromFile(app: Application, filename: String): MutableList<String?> {
        var words = emptyList<String?>().toMutableList()
        var tmp: String?
        try {
            app.assets.open(filename).bufferedReader().use {
                do {

                    tmp = it.readLine()

                    if (tmp == null) {
                        return words
                    } else {
                        words.add(tmp)
                    }

                } while (true)

            }
        } catch (ex: Exception) {
            print(ex)
        }
        return words
    }
}