package com.example.mynotesexample.ui.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotesexample.NoteEntity
import com.example.mynotesexample.data.NotesRepository
import com.example.mynotesexample.domain.GetNoteByIdUseCase
import com.example.mynotesexample.domain.SaveNoteUseCase
import com.example.mynotesexample.ui.detail.view.DetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.detail
 *
 * Created by Rafael Barbeyto Torrellas on 02/03/2023 at 11:07
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class DetailViewModel @Inject constructor (
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId:Int
    ) : ViewModel() {

    private val _state = MutableStateFlow(NoteEntity(0,"",""))
    val state: StateFlow<NoteEntity> = _state.asStateFlow()

    init{
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            if(note != null){
                _state.value = note
            }
        }
    }
    fun save(title: String, description: String) {
        viewModelScope.launch {
            val note = _state.value.copy(title = title, description = description)
            saveNoteUseCase(note)
        }
    }
}

/** Con DaggerHilt no hace falta ya la factory ya que lo gestiona de forma autonoma
@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId:Int
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(getNoteByIdUseCase,saveNoteUseCase,noteId) as T
    }
}*/