package com.example.notesapp.Adapters

import android.content.Context
import android.provider.SyncStateContract.Helpers.update
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.Activitys.MainActivity
import com.example.notesapp.Database.RoomDB
import com.example.notesapp.Models.Notes
import com.example.notesapp.R

class NotesAdapter(list: List<Notes>) : Adapter<NotesAdapter.NotesHolder>() {
    var list = list
    lateinit var db:RoomDB
    lateinit var context: Context
    class NotesHolder(itemView: View) : ViewHolder(itemView) {
        var txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var txtNote = itemView.findViewById<TextView>(R.id.txtNote)
        var cardNote = itemView.findViewById<CardView>(R.id.cardNote)
        var imgPin = itemView.findViewById<ImageView>(R.id.imgPin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        context = parent.context
        return NotesHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false))
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {

        db = RoomDB.getInstance(context)

        holder.txtTitle.text = list.get(position).title
        holder.txtNote.text = list.get(position).notes
        holder.cardNote.setCardBackgroundColor(list.get(position).color)

        if (list.get(position).pinned) {
            holder.imgPin.setImageResource(R.drawable.pin_fill)
        } else {
            holder.imgPin.setImageResource(R.drawable.pin)
        }

        holder.imgPin.setOnClickListener{

            if (list.get(position).pinned) {
                var data = Notes(list.get(position).title,list.get(position).notes,list.get(position).date,list.get(position).color,false)
                data.id = list.get(position).id
                db.notes().updateNote(data)
            } else {
                var data = Notes(list.get(position).title,list.get(position).notes,list.get(position).date,list.get(position).color,true)
                data.id = list.get(position).id
                db.notes().updateNote(data)
            }
            MainActivity.update()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}