package com.example.mynotesexample.di

import android.app.Application
import androidx.room.Room
import com.example.mynotesexample.data.NoteDatabase
import com.example.mynotesexample.data.NotesLocalDataSource
import com.example.mynotesexample.data.NotesRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.di
 *
 * Created by Rafael Barbeyto Torrellas on 06/03/2023 at 13:10
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application) = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        "NotesDatabase"
    ).build()

    @Provides
    fun provideNotesDao(db: NoteDatabase) = db.notesDao()

    @Provides
    fun provideNotesLocalDataSource(notesRoomDataSource: NotesRoomDataSource): NotesLocalDataSource =
        notesRoomDataSource

}