package com.example.multipleviewtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.multipleviewtest.R
import com.example.multipleviewtest.data.Banner
import com.example.multipleviewtest.data.DataItem
import com.example.multipleviewtest.data.Product
import com.example.multipleviewtest.data.Title
import com.example.multipleviewtest.databinding.ItemBannerBinding
import com.example.multipleviewtest.databinding.ItemTitleBinding
import com.example.multipleviewtest.databinding.LayoutListProductBinding

class MainAdapter : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val TYPE_BANNER = 1
        const val TYPE_TITLE = 2

        const val TYPE_BEST_SALE = 3
        const val TYPE_NEWEST = 4
    }

    private val list: MutableList<DataItem> = mutableListOf()

    fun updateList(newList: List<DataItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position].viewType) {
            TYPE_TITLE -> R.layout.item_title
            TYPE_BANNER -> R.layout.item_banner
            TYPE_NEWEST -> R.layout.layout_list_product
            TYPE_BEST_SALE -> R.layout.layout_list_product
            else -> throw java.lang.IllegalArgumentException("Invalid param")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.item_title -> TitleViewHolder(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            R.layout.item_banner -> BannerViewHolder(
                ItemBannerBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            R.layout.layout_list_product -> ProductViewHolder(
                LayoutListProductBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw java.lang.IllegalArgumentException("Invalid params")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is ProductViewHolder -> {
                list[position].product?.let {
                    holder.bindProduct(list[position].viewType, it)
                }
            }
            is TitleViewHolder -> {
                list[position].title?.let {
                    holder.bindTitle(it)
                }
            }
            is BannerViewHolder -> {
                list[position].banner?.let {
                    holder.bindBanner(it)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ProductViewHolder(private val binding: LayoutListProductBinding) :
        ViewHolder(binding.root) {
        fun bindProduct(viewType: Int, listProduct: List<Product>) {
            binding.listRv.apply {
                layoutManager = when(viewType) {
                    TYPE_BEST_SALE -> GridLayoutManager(binding.root.context, 2)
                    else -> GridLayoutManager(binding.root.context, 3)
                }
                adapter = ProductAdapter().apply {
                    updateList(listProduct)
                }
            }
        }
    }

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        ViewHolder(binding.root) {
        fun bindBanner(banner: Banner) {
            binding.tv.text = banner.name
        }
    }

    inner class TitleViewHolder(private val binding: ItemTitleBinding) : ViewHolder(binding.root) {
        fun bindTitle(title: Title) {
            binding.tv.text = title.title
        }
    }
}