package com.example.mynotesexample.ui.main.viewmodel

import androidx.lifecycle.*
import com.example.mynotesexample.data.NoteDatabase
import com.example.mynotesexample.NoteEntity
import com.example.mynotesexample.data.NotesRepository
import com.example.mynotesexample.domain.DeleteNoteUseCase
import com.example.mynotesexample.domain.GetCurrentNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.main
 *
 * Created by Rafael Barbeyto Torrellas on 01/03/2023 at 21:36
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class MainViewModel @Inject constructor(
    getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val state = getCurrentNotesUseCase()

    fun onNoteDelete(note: NoteEntity) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }
}
/** Con DaggerHilt no hace falta ya la factory ya que lo gestiona de forma autonoma


/** hay que crear una Factory para poder pasar el argumento al constructor, ya que el ViewModel
 * se crea una sola vez y no se podría actualizar ese dato
 */
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCurrentNotesUseCase,deleteNoteUseCase) as T
    }
}*/