package com.example.mynotesexample.data

import androidx.room.*
import com.example.mynotesexample.NoteEntity
import kotlinx.coroutines.flow.Flow

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample
 *
 * Created by Rafael Barbeyto Torrellas on 01/03/2023 at 11:04
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/


    @Dao
    interface NotesDao {
        @Query("SELECT * FROM NoteEntity")
        fun getAll(): Flow<List<NoteEntity>>

        @Query("SELECT * FROM NoteEntity WHERE id = :id ")
        suspend fun getById(id: Int): NoteEntity
        //fun getById(id: Int): Flow<NoteEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(note: NoteEntity)

        @Update
        suspend fun update(note: NoteEntity)

        @Delete
        suspend fun delete(note: NoteEntity)
    }
