package r.stookey.hikr.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context
import r.stookey.hikr.db.dao.PostDAO
import r.stookey.hikr.db.dao.UserDAO
import r.stookey.hikr.db.entity.PostEntity
import r.stookey.hikr.db.entity.UserEntity


@Database(entities = arrayOf(PostEntity::class, UserEntity::class), version = 1)
abstract class HikrDatabase: RoomDatabase() {


    /*
    Singleton Pattern for Database Initialization
*/
    companion object {
        private lateinit var INSTANCE: HikrDatabase
        fun getInstance(context: Context): HikrDatabase {
            if(INSTANCE == null){
                synchronized(HikrDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HikrDatabase::class.java, "hikr.db")
                            .build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun getUserDao(): UserDAO

    abstract fun getPostDao(): PostDAO

    /**
     * Deletes all rows from all the tables that are registered to this database as
     * [Database.entities].
     *
     *
     * This does NOT reset the auto-increment value generated by [PrimaryKey.autoGenerate].
     *
     *
     * After deleting the rows, Room will set a WAL checkpoint and run VACUUM. This means that the
     * data is completely erased. The space will be reclaimed by the system if the amount surpasses
     * the threshold of database file size.
     *
     * @see [Database File Format](https://www.sqlite.org/fileformat.html)
     */
    override fun clearAllTables() {
    }

    /**
     * Called by the generated code when database is open.
     *
     *
     * You should never call this method manually.
     *
     * @param db The database instance.
     */
    override fun internalInitInvalidationTracker(db: SupportSQLiteDatabase) {
        super.internalInitInvalidationTracker(db)
    }

    /**
     * Creates the open helper to access the database. Generated class already implements this
     * method.
     * Note that this method is called when the RoomDatabase is initialized.
     *
     * @param config The configuration of the Room database.
     * @return A new SupportSQLiteOpenHelper to be used while connecting to the database.
     */
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Called when the RoomDatabase is created.
     *
     *
     * This is already implemented by the generated code.
     *
     * @return Creates a new InvalidationTracker.
     */
    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}