package com.example.multipleviewtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.multipleviewtest.data.Title
import com.example.multipleviewtest.databinding.ItemTitleBinding

class TitleAdapter : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val list: MutableList<Title> = mutableListOf()

    fun updateList(newList: List<Title>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class TitleViewHolder(private val binding: ItemTitleBinding) : ViewHolder(binding.root) {
        fun bind(title: Title) {
            binding.tv.text = title.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        return TitleViewHolder(
            ItemTitleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        val title = list[position]
        holder.bind(title)
    }
}