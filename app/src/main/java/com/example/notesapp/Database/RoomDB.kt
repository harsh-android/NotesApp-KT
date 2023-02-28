package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Models.Notes

@Database(entities = [Notes::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): RoomDB {
            var db = Room.databaseBuilder(context, RoomDB::class.java, "Notes")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return db
        }

    }
    abstract fun notes() : NotesDao
}