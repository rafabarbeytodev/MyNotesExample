package com.example.mynotesexample.domain

import com.example.mynotesexample.NoteEntity
import com.example.mynotesexample.data.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    operator fun invoke(): Flow<List<NoteEntity>> = notesRepository.currentNotes

}
