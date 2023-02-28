package com.example.notesapp.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.Models.Notes

@Dao
interface NotesDao {

    @Insert
    fun addNote(notes: Notes)

    @Query("SELECT * FROM notes")
    fun getNotes() : List<Notes>

    @Update
    fun updateNote(note:Notes)

}