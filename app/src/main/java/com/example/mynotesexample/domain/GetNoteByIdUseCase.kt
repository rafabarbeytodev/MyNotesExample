package com.example.mynotesexample.domain

import com.example.mynotesexample.data.NotesRepository
import javax.inject.Inject

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.domain
 *
 * Created by Rafael Barbeyto Torrellas on 06/03/2023 at 12:30
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class GetNoteByIdUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(noteId: Int) = notesRepository.getById(noteId)

}