package com.dicoding.kelassekolah

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridEventAdapter(private val listEvent: ArrayList<Event>, private val packageContext: Context) : RecyclerView.Adapter<GridEventAdapter.GridViewHolder>() {
    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_gallery, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listEvent[position].photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)

        holder.imgPhoto.setOnClickListener {
            val galleryDetailIntent = Intent(packageContext, GalleryDetail::class.java)
            galleryDetailIntent.putExtra(GalleryDetail.EXTRA_INDEX, position)
            packageContext.startActivity(galleryDetailIntent)
        }
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }
}