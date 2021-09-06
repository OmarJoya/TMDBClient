package com.ojoya.tmdbclient.presentation.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojoya.tmdbclient.R
import com.ojoya.tmdbclient.databinding.ActivityMovieBinding
import com.ojoya.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel

    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createMovieSubComponent()
            .inject(this)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionUpdate -> {
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        val response = movieViewModel.updateMovies()
        response.observe(this, { movies ->
            movies?.let {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            }
            binding.movieProgressBar.visibility = View.GONE
        })
    }

    private fun initRecyclerView() {
        with(binding) {
            movieRecyclerView.layoutManager = LinearLayoutManager(this@MovieActivity)
            adapter = MovieAdapter()
            movieRecyclerView.adapter = adapter
            displayPopularMovies()
        }
    }

    private fun displayPopularMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this, {
            it?.let { movies ->
                adapter.setList(movies)
                adapter.notifyDataSetChanged()
            }
            binding.movieProgressBar.visibility = View.GONE
        })
    }
}