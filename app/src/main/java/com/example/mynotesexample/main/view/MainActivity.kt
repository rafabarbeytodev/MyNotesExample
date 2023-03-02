package com.example.mynotesexample.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mynotesexample.detail.view.DetailActivity
import com.example.mynotesexample.NoteAdapter
import com.example.mynotesexample.NoteApplication
import com.example.mynotesexample.NoteDatabase
import com.example.mynotesexample.databinding.ActivityMainBinding
import com.example.mynotesexample.main.MainViewModel
import com.example.mynotesexample.main.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: NoteAdapter
    private lateinit var database: NoteDatabase

    /** Se genera la instancia al ViewModel usando la Factory */
    private val vm by viewModels<MainViewModel> {
        MainViewModelFactory(
            (application as NoteApplication).noteDatabase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = (application as NoteApplication).noteDatabase
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            mAdapter = NoteAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelete = { vm.onNoteDelete(it) }
            )
            recyclerView.adapter = mAdapter
            fabAddNote.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }

            /**Con lo SateFlow no es tan sencilla la suscripción como con LiveData
             * y es posible que no se desuscriba correctamente.
             * Por ello se usa repeatOnLifecycle indicandole en que momento
             * debe desuscribirse. Habitual = STARTED*/
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect { notes ->
                        mAdapter.submitList(notes)
                    }
                }
            }
        }
    }
}