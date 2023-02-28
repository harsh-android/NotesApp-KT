package com.example.notesapp.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.Adapters.NotesAdapter
import com.example.notesapp.Database.RoomDB
import com.example.notesapp.Models.Notes
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    companion object {
        lateinit var binding: ActivityMainBinding
        lateinit var db: RoomDB
        var noteList = ArrayList<Notes>()

        fun update() {
            var list = db.notes().getNotes()
            list = list.reversed()
            noteList.clear()
            for (l in list) {
                if (l.pinned) {
                    noteList.add(l)
                }
            }
            for (l in list) {
                if (!l.pinned) {
                    noteList.add(l)
                }
            }
            binding.rcvNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.rcvNotes.adapter = NotesAdapter(noteList)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.getInstance(this)

        binding.fabtnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNoteActivity::class.java))
        }

        update()
    }

    override fun onResume() {
        super.onResume()
        update()
    }

}