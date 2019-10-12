package com.vn.f49kh.adapter.dinhgiataisan

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowItemDinhGiaTaiSanBinding
import com.vn.f49kh.model.home.ItemHomeDTO
import extension.setOnSingleClickListener
import kotlinx.android.synthetic.main.row_item_dinh_gia_tai_san.view.*
import kotlinx.android.synthetic.main.tab_main_view.view.*

class DinhGiaTaiSanAdapter(
    var items: MutableList<ItemHomeDTO>,
    var heightRV: Int,
    var clickItem: (ItemHomeDTO) -> Unit
) :
    RecyclerView.Adapter<DinhGiaTaiSanAdapter.ViewHolder>() {
    var heightItem = 1
    var positionClicked = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowItemDinhGiaTaiSanBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_item_dinh_gia_tai_san, parent, false)
        if (heightItem <= 1) {
            var heightEachDeco =
                parent.context?.resources?.getDimensionPixelSize(R.dimen.activity_horizontal_margin) ?: 16;
            heightItem = (heightRV - heightEachDeco * 4) / 3
        }
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<ItemHomeDTO>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowItemDinhGiaTaiSanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var item = items.getOrNull(position)
            item?.let {
                binding.item = item
                val layoutParams = itemView.layoutParams
                layoutParams.height = heightItem
                if (positionClicked == position) {
                    itemView.ctItem.setBackgroundResource(R.drawable.bg_corner_green)
                    itemView.imgDoVat.setColorFilter(Color.WHITE)
                    itemView.tvName.setTextColor(Color.WHITE)
                } else {
                    itemView.ctItem.setBackgroundResource(R.drawable.bg_boder_gray_bg_white)
                    itemView.imgDoVat.setColorFilter(null)
                    itemView.tvName.setTextColor(Color.parseColor("#3a3a3a"))
                }
                binding.root.setOnSingleClickListener {
                    positionClicked = position
                    notifyDataSetChanged()
                    clickItem.invoke(item)
                }
                binding.executePendingBindings()
            }
        }
    }


}
