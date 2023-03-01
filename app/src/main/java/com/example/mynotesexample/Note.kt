package com.example.mynotesexample

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample
 *
 * Created by Rafael Barbeyto Torrellas on 27/02/2023 at 16:40
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
)






