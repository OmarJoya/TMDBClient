package com.ojoya.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojoya.tmdbclient.data.model.artist.Artist
import com.ojoya.tmdbclient.databinding.ListItemBinding

class ArtistAdapter: RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {
    private var artistList = ArrayList<Artist>()

    fun setList(artists: List<Artist>) {
        artistList.clear()
        artistList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistList[position])
    }

    override fun getItemCount(): Int = artistList.size

    class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist) = with(binding) {
            titleTextView.text = artist.name
            descriptionTextView.text = artist.popularity.toString()
            val posterUrl = "https://image.tmdb.org/t/p/w500/${artist.profilePath}"
            Glide.with(imageView.context).load(posterUrl).into(imageView)
        }
    }
}