package com.example.mynotesexample.ui.detail.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mynotesexample.NoteEntity
import com.example.mynotesexample.databinding.ActivityDetailBinding
import com.example.mynotesexample.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val vm: DetailViewModel by viewModels()
    /** Esto sobra con Dagger Hilt
    {
        val database = (application as NoteApplication).noteDatabase
        val noteId = intent.getIntExtra(EXTRA_NOTE_ID,0)
        val notesDataSource = NotesRoomDataSource(database.notesDao())
        val notesRepository = NotesRepository(notesDataSource)
        val saveNoteUseCase = SaveNoteUseCase(notesRepository)
        val getNoteUseCase = GetNoteByIdUseCase(notesRepository)
        DetailViewModelFactory(getNoteUseCase,saveNoteUseCase, noteId)
    }*/

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
            btnSave.setOnClickListener {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                vm.save(title,description)
                finish()
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect{ note->
                        if(note != null){
                        etTitle.setText(note.title)
                        etDescription.setText(note.description)
                        }
                    }
                }
            }
        }
    }
}