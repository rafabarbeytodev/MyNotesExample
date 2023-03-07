package com.example.mynotesexample.data

import com.example.mynotesexample.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NotesLocalDataSource {
    val currentNotes: Flow<List<NoteEntity>>

    suspend fun delete(note: NoteEntity)

    suspend fun getById(noteId: Int): NoteEntity

    suspend fun save(note: NoteEntity)
}

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.data
 *
 * Created by Rafael Barbeyto Torrellas on 06/03/2023 at 11:13
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class NotesRoomDataSource @Inject constructor(private val notesDao: NotesDao) : NotesLocalDataSource {

    override val currentNotes: Flow<List<NoteEntity>> = notesDao.getAll()

    override suspend fun delete(note: NoteEntity) {
        notesDao.delete(note)
    }

    override suspend fun getById(noteId: Int): NoteEntity = notesDao.getById(noteId)

    override suspend fun save(note: NoteEntity) {
        notesDao.insert(note)
    }
}