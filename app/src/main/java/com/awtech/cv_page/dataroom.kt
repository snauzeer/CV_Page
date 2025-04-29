package com.awtech.cv_page

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class Person(
    @PrimaryKey var email: String,
    var name: String,
    val password: String,
    val lastname: String,
    var phonenumber: String,
    var Webpage: String,
    var LinkedIn: String,
    var Github: String,
    val qrCode: String
)


@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

    @Insert
    fun insertAll(person: Person)

    @Delete
    fun delete(person: Person)

    @Update
    fun update(person: Person)

    @Query("SELECT * FROM Person WHERE email = :email LIMIT 1")
    fun getPersonByEmail(email: String): Person?

}


@Database(entities = [Person::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}

/*
@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}*/