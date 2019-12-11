package com.vn.f49kh.adapter.chitiet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.ChitietRowBinding
import com.vn.f49kh.model.chitiet.ChiTietDTO


class ChiTietAdapter(
    var items: MutableList<ChiTietDTO>) :
    RecyclerView.Adapter<ChiTietAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ChitietRowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.chitiet_row, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<ChiTietDTO>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ChitietRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var item = items.getOrNull(position)
            item?.let {
                binding.data = it
                binding.executePendingBindings()
            }
        }
    }
}