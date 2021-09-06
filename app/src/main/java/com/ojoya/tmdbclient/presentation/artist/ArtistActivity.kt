package com.ojoya.tmdbclient.presentation.artist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojoya.tmdbclient.R
import com.ojoya.tmdbclient.databinding.ActivityArtistBinding
import com.ojoya.tmdbclient.databinding.ActivityMovieBinding
import com.ojoya.tmdbclient.presentation.di.Injector
import com.ojoya.tmdbclient.presentation.movie.MovieAdapter
import com.ojoya.tmdbclient.presentation.movie.MovieViewModel
import com.ojoya.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel

    private lateinit var binding: ActivityArtistBinding
    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createArtistSubComponent().inject(this)
        artistViewModel = ViewModelProvider(this, factory)[ArtistViewModel::class.java]

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
                updateArtists()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateArtists() {
        binding.artistProgressBar.visibility = View.VISIBLE
        val response = artistViewModel.updateArtists()
        response.observe(this) { artists ->
            artists?.let {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            }
            binding.artistProgressBar.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            artistRecyclerView.layoutManager = LinearLayoutManager(this@ArtistActivity)
            adapter = ArtistAdapter()
            artistRecyclerView.adapter = adapter
            displayPopularArtists()
        }
    }

    private fun displayPopularArtists() {
        binding.artistProgressBar.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.getArtists()
        responseLiveData.observe(this, {
            it?.let { artists ->
                adapter.setList(artists)
                adapter.notifyDataSetChanged()
            }
            binding.artistProgressBar.visibility = View.GONE
        })
    }
}