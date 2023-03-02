package com.example.mynotesexample.detail.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mynotesexample.NoteApplication
import com.example.mynotesexample.NoteEntity
import com.example.mynotesexample.databinding.ActivityDetailBinding
import com.example.mynotesexample.detail.DetailViewModel
import com.example.mynotesexample.detail.DetailViewModelFactory
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {



    private val vm: DetailViewModel by viewModels {
        val database = (application as NoteApplication).noteDatabase
        val noteId = intent.getIntExtra(EXTRA_NOTE_ID,0)
        DetailViewModelFactory(database, noteId)
    }

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
                vm.save(NoteEntity(
                    id = 0,
                    title = title,
                    description = description
                ))
                finish()
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect{ note->
                        if(note != null)
                        etTitle.setText(note.title)
                        etDescription.setText(note.description)
                    }
                }
            }
        }
    }
}