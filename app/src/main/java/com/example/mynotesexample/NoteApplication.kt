package com.example.mynotesexample

import android.app.Application
import androidx.room.Room

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample
 *
 * Created by Rafael Barbeyto Torrellas on 01/03/2023 at 11:20
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class NoteApplication : Application() {

    lateinit var noteDatabase: NoteDatabase

    override fun onCreate() {
        super.onCreate()
        noteDatabase = Room.databaseBuilder(
            this, NoteDatabase::class.java,"NotesDatabase")
            .build()
    }
}

