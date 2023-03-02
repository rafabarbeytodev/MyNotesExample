package com.example.mynotesexample.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotesexample.NoteDatabase
import com.example.mynotesexample.NoteEntity
import kotlinx.coroutines.launch

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.detail
 *
 * Created by Rafael Barbeyto Torrellas on 02/03/2023 at 11:07
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class DetailViewModel(
    private val database: NoteDatabase,
    private val noteId:Int
    ) : ViewModel() {

    val state = database.notesDao().getById(noteId)

    fun save(note: NoteEntity){
        viewModelScope.launch {
            database.notesDao().insert(note)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val database: NoteDatabase,
    private val noteId:Int
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(database,noteId) as T
    }
}