package com.dicoding.kelassekolah

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListEventAdapter(private val listEvent: ArrayList<Event>, private val packageContext: Context) : RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDate: TextView = itemView.findViewById(R.id.tv_item_date)
        var tvParticipant: TextView = itemView.findViewById(R.id.tv_item_participant)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        var itemContainer: View = itemView.findViewById(R.id.item_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_gallery, parent, false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val event = listEvent[position]

        Glide.with(holder.itemView.context)
            .load(event.photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvName.text = event.name
        holder.tvDate.text = event.date
        holder.tvParticipant.text = event.participant

        holder.itemContainer.setOnClickListener {
            val galleryDetailIntent = Intent(packageContext, GalleryDetail::class.java)
            galleryDetailIntent.putExtra(GalleryDetail.EXTRA_INDEX, position)
            packageContext.startActivity(galleryDetailIntent)
        }
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }
}