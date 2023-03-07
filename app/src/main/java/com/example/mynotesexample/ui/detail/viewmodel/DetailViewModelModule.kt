package com.example.mynotesexample.ui.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.mynotesexample.ui.detail.view.DetailActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample.ui.detail
 *
 * Created by Rafael Barbeyto Torrellas on 06/03/2023 at 13:49
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Module
@InstallIn(ViewModelComponent::class)
object DetailViewModelModule {

    @Provides
    fun provideNoteId(savedStateHandle: SavedStateHandle) =
        requireNotNull(savedStateHandle.get<Int>(DetailActivity.EXTRA_NOTE_ID))
}