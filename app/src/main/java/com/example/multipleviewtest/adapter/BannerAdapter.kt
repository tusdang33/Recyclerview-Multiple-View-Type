package com.example.multipleviewtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewtest.data.Banner
import com.example.multipleviewtest.databinding.ItemBannerBinding

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    private val list: MutableList<Banner> = mutableListOf()

    fun updateList(newList: List<Banner>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Banner) {
            binding.tv.text = banner.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = list[position]
        holder.bind(banner)

    }
}