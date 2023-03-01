package com.example.mynotesexample

import android.content.Context
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesexample.databinding.ItemNotesBinding

/*****
 * Proyect: MyNotesExample
 * Package: com.example.mynotesexample
 *
 * Created by Rafael Barbeyto Torrellas on 27/02/2023 at 22:24
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class NoteAdapter(
    private val onNoteClick: (NoteEntity) -> Unit,
    private val onNoteDelete: (NoteEntity) -> Unit,
    ): ListAdapter<NoteEntity, RecyclerView.ViewHolder>(NoteDiffCallback()) {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_notes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val note = getItem(position)

        with(holder as ViewHolder) {
            bind(note,onNoteClick,onNoteDelete)
            mBinding.title.text = note.title
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val mBinding = ItemNotesBinding.bind(view)

    fun bind(note: NoteEntity, onNoteClick: (NoteEntity) -> Unit, onNoteDelete: (NoteEntity) -> Unit) {
        mBinding.title.text = note.title
        mBinding.root.setOnClickListener { onNoteClick(note) }
        mBinding.ivDeleteDelete.setOnClickListener { onNoteDelete(note) }
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<NoteEntity>() {
    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }
}



