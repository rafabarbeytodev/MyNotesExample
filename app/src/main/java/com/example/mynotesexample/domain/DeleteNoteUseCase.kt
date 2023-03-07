package com.example.mynotesexample.domain

import com.example.mynotesexample.NoteEntity
import com.example.mynotesexample.data.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note:NoteEntity){
        notesRepository.delete(note)
    }
}
