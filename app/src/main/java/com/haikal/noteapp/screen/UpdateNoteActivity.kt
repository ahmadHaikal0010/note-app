package com.haikal.noteapp.screen

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.haikal.noteapp.R
import com.haikal.noteapp.databinding.ActivityUpdateNoteBinding
import com.haikal.noteapp.helper.NoteDatabaseHelper
import com.haikal.noteapp.model.ModelNote

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        var noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.etEditJudul.setText(note.title)
        binding.etEditCatatan.setText(note.content)

        // proses update ke database
        binding.btnUpdateNote.setOnClickListener() {
            val newTitle = binding.etEditJudul.text.toString()
            val newContent = binding.etEditCatatan.text.toString()

            val updateNote = ModelNote(noteId, newTitle, newContent)
            db.updateNote(updateNote)
            finish()
        }
    }
}