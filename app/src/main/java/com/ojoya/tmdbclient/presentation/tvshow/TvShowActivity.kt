package com.ojoya.tmdbclient.presentation.tvshow

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojoya.tmdbclient.R
import com.ojoya.tmdbclient.databinding.ActivityTvShowBinding
import com.ojoya.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var binding: ActivityTvShowBinding
    private lateinit var adapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createTvShowSubComponent().inject(this)
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionUpdate -> {
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateTvShows() {
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val response = tvShowViewModel.updateTvShows()
        response.observe(this, Observer {
            it?.let { tvShows ->
                adapter.setList(tvShows)
                adapter.notifyDataSetChanged()
            }
            binding.tvShowProgressBar.visibility = View.GONE
        })
    }

    private fun initRecyclerView() {
        with(binding) {
            tvShowRecyclerView.layoutManager = LinearLayoutManager(this@TvShowActivity)
            adapter = TvShowAdapter()
            tvShowRecyclerView.adapter = adapter
            displayPopularTvShows()
        }
    }

    private fun displayPopularTvShows() {
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val response = tvShowViewModel.getTvShows()
        response.observe(this, Observer {
            it?.let { tvShows ->
                adapter.setList(tvShows)
                adapter.notifyDataSetChanged()
            }
            binding.tvShowProgressBar.visibility = View.GONE
        })

    }
}