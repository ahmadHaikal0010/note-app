package com.haikal.noteapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.haikal.noteapp.DetailPageActivity
import com.haikal.noteapp.R
import com.haikal.noteapp.helper.NoteDatabaseHelper
import com.haikal.noteapp.model.ModelNote
import com.haikal.noteapp.screen.UpdateNoteActivity

class NoteAdapter(
    private var notes: List<ModelNote>,
    context: Context
//    val getActivity: MainActivity
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val db : NoteDatabaseHelper = NoteDatabaseHelper(context)

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtJudul)
        val txtContent: TextView = itemView.findViewById(R.id.txtContent)
        val cardNote: CardView = itemView.findViewById(R.id.cardNote)
        val btnEdit: ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteData = notes[position]
        holder.txtTitle.text = noteData.title
        holder.txtContent.text = noteData.content
        holder.cardNote.setOnClickListener() {
            val intent = Intent(holder.itemView.context, DetailPageActivity::class.java)
            intent.putExtra("title", noteData.title)
            intent.putExtra("content", noteData.content)

            holder.itemView.context.startActivity(intent)
        }
        holder.btnDelete.setOnClickListener() {
            AlertDialog.Builder(holder.itemView.context).apply {
                setTitle("Confirmation")
                setMessage("Do you want to continue ?")
                setIcon(R.drawable.baseline_delete_24)

                setPositiveButton("Yes") {
                    dialogInterface, i->
                    db.deleteNote(noteData.id)
                    refreshData(db.getAllNotes())
                    Toast.makeText(holder.itemView.context, "Note berhasil dihapus", Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }

                setNeutralButton("No") {
                    dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show() // untuk menampilkan alert dialog
        }
        // update
        holder.btnEdit.setOnClickListener() {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", noteData.id)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    // fitur untuk auto refresh data
    fun refreshData(newNotes: List<ModelNote>) {
        notes = newNotes
        notifyDataSetChanged()
    }

}