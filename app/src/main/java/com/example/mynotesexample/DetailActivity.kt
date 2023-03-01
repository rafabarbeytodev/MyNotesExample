package com.example.mynotesexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.mynotesexample.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

  private lateinit var database: NoteDatabase

    companion object {
        const val EXTRA_NOTE_ID = "note_id"

        fun navigate(activity: AppCompatActivity, noteId: Int = -1) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ID, noteId)
            }
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
            database = (application as NoteApplication).noteDatabase
            lifecycleScope.launch {
                val note = database.notesDao().getById(intent.getIntExtra(EXTRA_NOTE_ID, -1))
                if (note != null) {
                    etTitle.setText(note.title)
                    etDescription.setText(note.description)
                }

                btnSave.setOnClickListener {
                    val title = etTitle.text.toString()
                    val description = etDescription.text.toString()
                    lifecycleScope.launch {
                        if(note != null) {
                            database.notesDao().update(note.copy(title = title, description = description))
                        }else{
                            database.notesDao().insert(NoteEntity(0,title = title, description = description))
                        }
                    }
                    finish()
                }
            }
        }
    }
}