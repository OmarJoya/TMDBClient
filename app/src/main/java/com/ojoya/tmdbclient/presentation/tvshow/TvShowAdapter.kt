package com.ojoya.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojoya.tmdbclient.data.model.tvshow.TvShow
import com.ojoya.tmdbclient.databinding.ListItemBinding

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {
    private var tvShowList = ArrayList<TvShow>()

    fun setList(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int = tvShowList.size

    class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) = with(binding) {
            titleTextView.text = tvShow.name
            descriptionTextView.text = tvShow.overview
            val posterUrl = "https://image.tmdb.org/t/p/w500/${tvShow.posterPath}"
            Glide.with(imageView.context).load(posterUrl).into(imageView)
        }
    }
}