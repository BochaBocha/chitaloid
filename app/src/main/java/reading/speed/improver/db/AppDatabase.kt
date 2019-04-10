package reading.speed.improver.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import reading.speed.improver.materials.Word
import reading.speed.improver.db.dao.PupilDao
import reading.speed.improver.db.dao.WordDao
import reading.speed.improver.user.pupil.Pupil

@Database(entities = [(Pupil::class),(Word::class)], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPupilDao(): PupilDao
    abstract fun getWordDao(): WordDao

    companion object {
        @JvmField
        val MIGRATION_1_2 : Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

    }
}