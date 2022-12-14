package com.dicoding.kelassekolah

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat

class GalleryDetail : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_INDEX = "extra_index"
        private const val STATE_COMMENT_NAME = "state_comment_name"
        private const val STATE_COMMENT_COMMENT = "state_comment_comment"
    }

    private lateinit var ivPhoto: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvParticipant: TextView
    private lateinit var tvNote: TextView
    private lateinit var btnLike: Button
    private lateinit var btnBookmark: Button
    private lateinit var btnEmail: Button
    private lateinit var edtName: EditText
    private lateinit var edtComment: EditText
    private lateinit var btnAddComment: Button
    private lateinit var containerComment: LinearLayout
    private lateinit var tvCommentName: TextView
    private lateinit var tvCommentComment: TextView

    private lateinit var event: Event

    private var isLiked: Boolean = false
    private var isBookmark: Boolean = false
    private var isCommentAdded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val index = intent.getIntExtra(EXTRA_INDEX, 0)
        setEventFromEventsData(index)

        setComponentValue(event.photo, event.name, event.date, event.participant, event.detail)

        btnLike = findViewById(R.id.btn_like)
        btnBookmark = findViewById(R.id.btn_bookmark)
        btnEmail = findViewById(R.id.btn_email)
        edtName = findViewById(R.id.edt_name)
        edtComment = findViewById(R.id.edt_comment)
        btnAddComment = findViewById(R.id.btn_add_comment)
        containerComment = findViewById(R.id.container_comment)
        btnLike.setOnClickListener(this)
        btnBookmark.setOnClickListener(this)
        btnEmail.setOnClickListener(this)
        btnAddComment.setOnClickListener(this)

        if (savedInstanceState != null) {
            val resultCommentName = savedInstanceState.getString(STATE_COMMENT_NAME)
            val resultCommentComment = savedInstanceState.getString(STATE_COMMENT_COMMENT)

            setInflaterGalleryComment(resultCommentName!!, resultCommentComment!!)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_COMMENT_NAME, tvCommentName.text.toString())
        outState.putString(STATE_COMMENT_COMMENT, tvCommentComment.text.toString())

        super.onSaveInstanceState(outState)
    }

    private fun setEventFromEventsData(position: Int) {
        event = EventsData.getData(position)
    }

    private fun setComponentValue(photo: Int, title: String, date: String, participant: String, note: String) {
        ivPhoto = findViewById(R.id.iv_photo)
        tvTitle = findViewById(R.id.tv_title)
        tvDate = findViewById(R.id.tv_date)
        tvParticipant = findViewById(R.id.tv_participant)
        tvNote = findViewById(R.id.tv_note)

        ivPhoto.setImageResource(photo)
        tvTitle.text = title
        tvDate.text = date
        tvParticipant.text = participant
        tvNote.text = note
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_like -> {
                if (!isLiked) {
                    setLikeLiked()
                } else {
                    setLikeUnliked()
                }

                isLiked = !isLiked
            }

            R.id.btn_bookmark -> {
                if (!isBookmark) {
                    setBookmarkBookmarked()
                } else {
                    setBookmarkNotBookmarked()
                }

                isBookmark = !isBookmark
            }

            R.id.btn_email -> {
                val sendToIntent = Intent(Intent.ACTION_SENDTO)
                sendToIntent.apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, "")
                    putExtra(Intent.EXTRA_SUBJECT, getString(R.string.school_class_class_gallery))
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.activity_name_activity_date_participant, tvTitle.text, tvDate.text, tvParticipant.text, tvNote.text))
                }

                startActivity(Intent.createChooser(sendToIntent, getString(R.string.share_with_email)))
            }

            R.id.btn_add_comment -> {
                val inputName = edtName.text.toString().trim()
                val inputComment = edtComment.text.toString().trim()

                var isAddCommentFieldsEmpty = false

                if (inputName.isEmpty()) {
                    edtName.setText(getString(R.string.anonym))
                }

                if (inputComment.isEmpty()) {
                    edtComment.error = getString(R.string.comment_should_not_empty)
                    isAddCommentFieldsEmpty = true
                }

                if (!isAddCommentFieldsEmpty) {
                    Toast.makeText(this@GalleryDetail, getString(R.string.add_comment_success_but_on_development), Toast.LENGTH_SHORT).show()

                    setInflaterGalleryComment(edtName.text.toString().trim(), edtComment.text.toString().trim())

                    isCommentAdded = true
                }
            }
        }
    }

    private fun setInflaterGalleryComment(name: String, comment: String) {
        val view: View = LayoutInflater.from(this@GalleryDetail).inflate(R.layout.item_comment_gallery, containerComment, !isCommentAdded)
        tvCommentName = view.findViewById(R.id.item_comment_name)
        tvCommentComment = view.findViewById(R.id.item_comment_comment)

        tvCommentName.text = name
        tvCommentComment.text = comment
    }

    private fun getWrappedDrawable(drawable: Int): Drawable {
        val unwrappedDrawable: Drawable =
            AppCompatResources.getDrawable(this@GalleryDetail, drawable)!!
        return DrawableCompat.wrap(unwrappedDrawable)
    }

    private fun setLikeLiked() {
        val wrappedDrawable: Drawable = getWrappedDrawable(R.drawable.ic_baseline_favorite_24_white)
        DrawableCompat.setTint(wrappedDrawable, Color.RED)

        btnLike.setCompoundDrawablesWithIntrinsicBounds(null, wrappedDrawable, null, null)
    }

    private fun setLikeUnliked() {
        val wrappedDrawable: Drawable = getWrappedDrawable(R.drawable.ic_baseline_favorite_24_white)
        DrawableCompat.setTint(wrappedDrawable, Color.GRAY)

        btnLike.setCompoundDrawablesWithIntrinsicBounds(null, wrappedDrawable, null, null)
    }

    private fun setBookmarkBookmarked() {
        val wrappedDrawable: Drawable = getWrappedDrawable(R.drawable.ic_baseline_bookmark_24_gray)
        DrawableCompat.setTint(wrappedDrawable, Color.BLUE)

        btnBookmark.setCompoundDrawablesWithIntrinsicBounds(null, wrappedDrawable, null, null)
    }

    private fun setBookmarkNotBookmarked() {
        val wrappedDrawable: Drawable = getWrappedDrawable(R.drawable.ic_baseline_bookmark_24_gray)
        DrawableCompat.setTint(wrappedDrawable, Color.GRAY)

        btnBookmark.setCompoundDrawablesWithIntrinsicBounds(null, wrappedDrawable, null, null)
    }
}