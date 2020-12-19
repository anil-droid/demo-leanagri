package com.demo.leanagri.ui.movie.listing

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.demo.leanagri.R
import com.demo.leanagri.core.Constants
import com.demo.leanagri.data.model.detail.MovieDetail
import com.demo.leanagri.databinding.ActivityMainBinding
import com.demo.leanagri.databinding.FilterSheetBinding
import com.demo.leanagri.ui.movie.detail.MoviesDetailActivity
import com.demo.leanagri.ui.movie.listing.adapter.MoviesListAdapter
import com.demo.leanagri.ui.movie.viewmodel.MoviesViewModel
import com.demo.leanagri.utils.asc
import com.demo.leanagri.utils.callbacks.IItemClickCallback
import com.demo.leanagri.utils.callbacks.PaginationScrollListener
import com.demo.leanagri.utils.desc
import com.demo.leanagri.utils.setOnClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesListActivity : AppCompatActivity(),
    IItemClickCallback<MovieDetail>, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MoviesViewModel by viewModels()
    private val dialog: BottomSheetDialog by lazy {

        BottomSheetDialog(this@MoviesListActivity)
            .apply {
                FilterSheetBinding
                    .inflate(layoutInflater)
                    .run {
                        setContentView(root)
                        arrayOf(
                            tvPopDes,
                            tvPopAsc,
                            tvRatingAsc,
                            tvRatingDsc,
                            tvReleaseAsc,
                            tvReleaseDsc
                        ).setOnClickListener(this@MoviesListActivity)
                    }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.run{
           setContentView(root)
            filter.setOnClickListener(this@MoviesListActivity)
        }


        binding.recyclerView.addOnScrollListener(object : PaginationScrollListener() {
            override fun loadMoreItems() {
                viewModel.loadMovies()
            }

            override fun isLastPage(): Boolean = viewModel.isLastPage

            override fun isLoading(): Boolean = viewModel.isLoading

        })

        viewModel.loadMovies()
        viewModel.movies.observe(this, { resource ->

            resource.takeIf { it.isNotEmpty() }?.run {
                binding.recyclerView.adapter?.let {
                    (it as MoviesListAdapter).updateData(this)
                } ?: run {
                    binding.recyclerView.adapter = MoviesListAdapter(this, this@MoviesListActivity)
                }
            }

            viewModel.isLoading = false

        })
    }

    override fun onItemClick(data: MovieDetail) {
        val detailActivityIntent = Intent(this, MoviesDetailActivity::class.java).apply {
            putExtra(MoviesDetailActivity.IntentKeys.MOVIE_ID, data.id ?: -1)
        }
        startActivity(detailActivityIntent)
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.tv_pop_des -> {
                applyFilter(Constants.Filter.POPULARITY.desc())
            }
            R.id.tv_pop_asc -> {
                applyFilter(Constants.Filter.POPULARITY.asc())
            }
            R.id.tv_rating_asc -> {
                applyFilter(Constants.Filter.VOTE.asc())
            }
            R.id.tv_rating_dsc -> {
                applyFilter(Constants.Filter.VOTE.desc())
            }
            R.id.tv_release_asc -> {
                applyFilter(Constants.Filter.RELEASE_DATE.asc())
            }
            R.id.tv_release_dsc -> {
                applyFilter(Constants.Filter.RELEASE_DATE.desc())
            }
            R.id.filter -> {
                dialog.show()
            }

        }

    }

    private fun applyFilter(filter: String) {
        dialog.dismiss()
        resetList()
        viewModel.applyFilter(filter)
    }

    private fun resetList() {
        (binding.recyclerView.adapter as MoviesListAdapter?)?.clearData()
    }

}


