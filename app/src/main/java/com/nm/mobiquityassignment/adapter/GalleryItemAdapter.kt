package com.nm.mobiquityassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nm.mobiquityassignment.databinding.GalleryItemLayoutBinding
import com.nm.mobiquityassignment.db.model.GalleryItem
import com.nm.mobiquityassignment.ui.fragments.GalleryFragment
import com.squareup.picasso.Picasso

class GalleryItemAdapter(val onBottomReachedListener: OnBottomReachedListener) : RecyclerView.Adapter<GalleryViewHolder>() {

    var galleryItems = mutableListOf<GalleryItem>()

    fun setGalleries(galleryItems: List<GalleryItem>) {
        this.galleryItems = galleryItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryItemLayoutBinding.inflate(inflater, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val galleryItem = galleryItems[position]
        holder.binding.name.text = galleryItem.title
        Picasso.get()
            .load(galleryItem.thumbnailUrl)
            .into(holder.binding.imageview)
        if(position == galleryItems.size-1){
            onBottomReachedListener.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        return galleryItems.size
    }
}

class GalleryViewHolder(val binding: GalleryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}

interface OnBottomReachedListener{
    fun onBottomReached()
}