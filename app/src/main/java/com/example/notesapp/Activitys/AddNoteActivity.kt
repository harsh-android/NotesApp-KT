package com.example.notesapp.Activitys

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.notesapp.Database.RoomDB
import com.example.notesapp.Models.Notes
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityAddNoteBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNoteBinding
    var selectColor = 0

    lateinit var db: RoomDB

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.getInstance(this)

        binding.cardAdd.setOnClickListener {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a")
            val current = LocalDateTime.now().format(formatter)

            var note = Notes(
                binding.edtTitles.text.toString(),
                binding.edtNotes.text.toString(), current, selectColor, false
            )
            db.notes().addNote(note)
            finish()
        }

        binding.cardColors.setOnClickListener {

            MaterialColorPickerDialog
                .Builder(this)
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE)
                .setColorListener { color, colorHex ->
                    binding.cardColors.setCardBackgroundColor(color)
                    selectColor = color
                }
                .show()

        }

    }
}