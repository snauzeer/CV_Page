package com.awtech.cv_page

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Person(
    @PrimaryKey val email: String,
    val name: String,
    val password: Int
)


@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

    @Insert
    fun insertAll(person: Person)

    @Delete
    fun delete(person: Person)

}



@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}