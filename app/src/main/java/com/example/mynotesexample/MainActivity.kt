package com.example.mynotesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.mynotesexample.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: NoteAdapter
    private lateinit var database: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            database = (application as NoteApplication).noteDatabase
            mAdapter = NoteAdapter(
                onNoteClick ={ DetailActivity.navigate(this@MainActivity,it.id) },
                onNoteDelete = {
                    lifecycleScope.launch {
                        database.notesDao().delete(it)
                        mAdapter.submitList(database.notesDao().getAll())
                    }
                }
            )
            recyclerView.adapter = mAdapter
            fabAddNote.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val notes = database.notesDao().getAll()
            mAdapter.submitList(notes)
        }
    }

}