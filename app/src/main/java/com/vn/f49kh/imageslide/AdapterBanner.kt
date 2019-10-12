package com.xxx.baseproject.imageslide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowBannerMatchparentBinding
import extension.setOnSingleClickListener

class AdapterBanner(
    var items: MutableList<String>,
    var isSquareCardView: Boolean,
    var isImageType: Int,
    var eventClickImageBanner: ((View, Int) -> Unit)?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowBannerMatchparentBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_banner_matchparent, parent, false)
        return ViewHolderMatchParent(binding)
    }

    fun setListImage(listImage: MutableList<String>, type: Int) {
        items = listImage
        isImageType = type
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderMatchParent) {
            holder.bind(position)
        }
    }


    inner class ViewHolderMatchParent(var binding: RowBannerMatchparentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var imageURL: MutableLiveData<String> = MutableLiveData()
        fun bind(position: Int) {
            binding.item = this@ViewHolderMatchParent
            binding.root.setOnSingleClickListener {
                eventClickImageBanner?.invoke(binding.img, position)
            }
            imageURL.value = items.getOrNull(position)
            binding.executePendingBindings()
        }
    }

}