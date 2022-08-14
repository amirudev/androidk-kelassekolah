package com.dicoding.kelassekolah

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewEventAdapter(private val listEvent: ArrayList<Event>, private val packageContext: Context?) : RecyclerView.Adapter<CardViewEventAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDate: TextView = itemView.findViewById(R.id.tv_item_date)
        var tvParticipant: TextView = itemView.findViewById(R.id.tv_item_participant)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var tvLikes: TextView = itemView.findViewById(R.id.tv_item_likes)
        var btnLikes: Button = itemView.findViewById(R.id.btn_like)
        var btnShare: Button = itemView.findViewById(R.id.btn_share)
        var btnViewDetail: Button = itemView.findViewById(R.id.btn_view_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_gallery, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val event = listEvent[position]

        Glide.with(holder.itemView.context)
            .load(event.photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)

        holder.tvName.text = event.name
        holder.tvDate.text = event.date
        holder.tvDetail.text = event.detail
        holder.tvParticipant.text = event.participant
        holder.tvLikes.text = event.likes.toString()

        holder.btnLikes.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Like", Toast.LENGTH_SHORT).show()
            updateLikeCount(holder)
        }

        holder.btnShare.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Share", Toast.LENGTH_SHORT).show()
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, packageContext?.getString(R.string.school_gallery))
                putExtra(Intent.EXTRA_TEXT,
                    packageContext?.getString(R.string.activity_get_likes_download_class_school, holder.tvName.text, holder.tvLikes.text)
                )
            }

            packageContext?.startActivity(Intent.createChooser(shareIntent, packageContext?.getString(R.string.share_class_school)))
        }

        holder.btnViewDetail.setOnClickListener {
            val galleryDetailIntent = Intent(packageContext, GalleryDetail::class.java)
            galleryDetailIntent.putExtra(GalleryDetail.EXTRA_INDEX, position)
            packageContext?.startActivity(galleryDetailIntent)
        }
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    private fun updateLikeCount(holder: CardViewViewHolder) {
        var likesCount = holder.tvLikes.text.toString().toInt()
        likesCount++

        holder.tvLikes.text = likesCount.toString()
    }
}