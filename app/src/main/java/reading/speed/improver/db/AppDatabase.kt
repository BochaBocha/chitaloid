package reading.speed.improver.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import reading.speed.improver.db.dao.*
import reading.speed.improver.exercises.Exercise
import reading.speed.improver.exercises.materials.Word
import reading.speed.improver.exercises.materials.sentence.Sentence
import reading.speed.improver.exercises.materials.Text
import reading.speed.improver.statistic.Statistic
import reading.speed.improver.user.pupil.Pupil

@Database(
    entities = [(Pupil::class), (Word::class), (Text::class),
        (Sentence::class), (Statistic::class), (Exercise::class)],
    version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPupilDao(): PupilDao
    abstract fun getWordDao(): WordDao
    abstract fun getTextDao(): TextDao
    abstract fun getSentenceDao(): SentenceDao
    abstract fun getStatisticDao(): StatisticDao
    abstract fun getExerciseDao(): ExerciseDao

    companion object {
        @JvmField
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }
}