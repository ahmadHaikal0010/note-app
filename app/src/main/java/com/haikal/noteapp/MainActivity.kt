package com.haikal.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.noteapp.adapter.NoteAdapter
import com.haikal.noteapp.databinding.ActivityMainBinding
import com.haikal.noteapp.helper.NoteDatabaseHelper
import com.haikal.noteapp.screen.AddNoteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        db = NoteDatabaseHelper(this)
        noteAdapter = NoteAdapter(db.getAllNotes(), this)

        binding.notesRecycleview.layoutManager = LinearLayoutManager(this)
        binding.notesRecycleview.adapter = noteAdapter

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    override fun onResume() {
        super.onResume()
        val notes = db.getAllNotes()
        noteAdapter.refreshData(notes)
    }

    // TASK
    // ketika item list note di klik, akan pindah ke page detail sesuai dengan item dan content
}