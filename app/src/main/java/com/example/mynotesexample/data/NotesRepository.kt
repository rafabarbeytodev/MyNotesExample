package com.example.mynotesexample.data

import com.example.mynotesexample.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.data
 *
 * Created by Rafael Barbeyto Torrellas on 06/03/2023 at 10:07
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class NotesRepository @Inject constructor(private val notesDataSource: NotesLocalDataSource) {

    val currentNotes: Flow<List<NoteEntity>> = notesDataSource.currentNotes

    suspend fun delete(note: NoteEntity) {
        notesDataSource.delete(note)
    }

    suspend fun getById(noteId: Int): NoteEntity = notesDataSource.getById(noteId)

    suspend fun save(note: NoteEntity) {
        notesDataSource.save(note)
    }


}