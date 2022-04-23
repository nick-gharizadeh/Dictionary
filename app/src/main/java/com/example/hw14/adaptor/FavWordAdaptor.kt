package com.example.hw14.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw14.R
import com.example.hw14.databinding.ItemfavBinding
import com.example.hw14.model.Word


typealias FavClickHandler = (Word) -> Unit

class FavWordAdaptor(var clickHandler: FavClickHandler) : ListAdapter<Word, FavWordAdaptor.ItemHolder>(FavWordDiffCallback) {

    class ItemHolder(val binding: ItemfavBinding): RecyclerView.ViewHolder(binding.root)

    object FavWordDiffCallback : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: ItemfavBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.itemfav,
            parent,
            false
        )
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.word = getItem(position)
        holder.binding.textViewWordItemFav.setOnClickListener {
            clickHandler.invoke(getItem(position))
        }
        holder.binding.textViewMeaningItemFav.setOnClickListener {
            clickHandler.invoke(getItem(position))
        }
    }


}