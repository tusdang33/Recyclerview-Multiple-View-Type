package com.example.multipleviewtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multipleviewtest.adapter.MainAdapter
import com.example.multipleviewtest.adapter.MainAdapter.Companion.TYPE_BANNER
import com.example.multipleviewtest.adapter.MainAdapter.Companion.TYPE_BEST_SALE
import com.example.multipleviewtest.adapter.MainAdapter.Companion.TYPE_NEWEST
import com.example.multipleviewtest.adapter.MainAdapter.Companion.TYPE_TITLE
import com.example.multipleviewtest.data.Banner
import com.example.multipleviewtest.data.DataItem
import com.example.multipleviewtest.data.Product
import com.example.multipleviewtest.data.Title
import com.example.multipleviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter().apply {
                updateList(setupData())
            }
        }
    }

    private fun setupData(): MutableList<DataItem> {
        val list = mutableListOf<DataItem>()
        list.add(DataItem(TYPE_BANNER).apply {
            banner = Banner("Banner 1")
        })
        list.add(DataItem(TYPE_TITLE).apply {
            title = Title("Title 1")
        })
        list.add(DataItem(TYPE_NEWEST).apply {
            val listProduct = mutableListOf<Product>()
            for (i in 1..6) {
                listProduct.add(Product("Product $i"))
            }
            product = listProduct
        })
        list.add(DataItem(TYPE_TITLE).apply {
            title = Title("Title 1")
        })
        list.add(DataItem(TYPE_BEST_SALE).apply {
            val listProduct = mutableListOf<Product>()
            for (i in 7..12) {
                listProduct.add(Product("Product $i"))
            }
            product = listProduct
        })

        return list
    }
}