package com.example.mynotesexample.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotesexample.NoteEntity

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample
 *
 * Created by Rafael Barbeyto Torrellas on 01/03/2023 at 10:56
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
