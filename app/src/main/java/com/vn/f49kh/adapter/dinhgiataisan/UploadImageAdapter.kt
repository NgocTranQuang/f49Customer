package com.vn.f49kh.adapter.dinhgiataisan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowUploadImageBinding
import com.vn.f49kh.model.dinhgiataisan.UploadImageDTO
import extension.setOnSingleClickListener


class UploadImageAdapter(var items: MutableList<UploadImageDTO>) : RecyclerView.Adapter<UploadImageAdapter.ViewHolder>() {
    var heightItem = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowUploadImageBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_upload_image, parent, false)
//        if (heightItem == 1) {
//            heightItem = (parent.measuredHeight - (((parent.context?.resources?.getDimensionPixelSize(R.dimen.height_line_size)
//                ?: 1) * 3))) / 4
//        }
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<UploadImageDTO>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowUploadImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
//            val layoutParams = itemView.layoutParams
//            layoutParams.height = heightItem
            var item = items.getOrNull(position)
            item?.let {
                binding.item = it
                binding.imgDelete.setOnSingleClickListener {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
//                itemView.setOnSingleClickListener {
//                    var list = items.map { it.uri }
//                    ImageViewer.Builder(itemView.context, list).setStartPosition(position).show()
//                }
                binding.executePendingBindings()
            }

        }
    }


}


