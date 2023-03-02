package com.example.mynotesexample.main

import androidx.lifecycle.*
import com.example.mynotesexample.NoteDatabase
import com.example.mynotesexample.NoteEntity
import kotlinx.coroutines.launch

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.main
 *
 * Created by Rafael Barbeyto Torrellas on 01/03/2023 at 21:36
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class MainViewModel(private val db: NoteDatabase) : ViewModel() {

    val state = db.notesDao().getAll()

    fun onNoteDelete(note: NoteEntity) {
        viewModelScope.launch {
            db.notesDao().delete(note)
        }
    }
}

/** hay que crear una Factory para poder pasar el argumento al constructor, ya que el ViewModel
 * se crea una sola vez y no se podría actualizar ese dato
 */
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val db: NoteDatabase): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }
}