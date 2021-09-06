package com.ojoya.tmdbclient.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojoya.tmdbclient.data.model.movie.Movie
import com.ojoya.tmdbclient.databinding.ListItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            titleTextView.text = movie.title
            descriptionTextView.text = movie.title
            val posterUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
            Glide.with(imageView.context).load(posterUrl).into(imageView)
        }
    }
}