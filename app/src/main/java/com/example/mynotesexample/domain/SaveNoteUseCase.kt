package com.example.mynotesexample.domain

import com.example.mynotesexample.NoteEntity
import com.example.mynotesexample.data.NotesRepository
import javax.inject.Inject

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.domain
 *
 * Created by Rafael Barbeyto Torrellas on 06/03/2023 at 12:32
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class SaveNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: NoteEntity){
        notesRepository.save(note)
    }
}