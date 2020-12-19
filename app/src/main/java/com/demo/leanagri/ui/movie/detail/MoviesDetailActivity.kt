package com.demo.leanagri.ui.movie.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.demo.leanagri.R
import com.demo.leanagri.databinding.MovieDetailBinding
import com.demo.leanagri.ui.movie.viewmodel.MoviesViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MoviesDetailActivity : AppCompatActivity(){

    private val viewModel: MoviesViewModel by viewModels()

    object IntentKeys {

        const val MOVIE_ID =  "MOVIE_ID"
    }

    @Inject
    lateinit var gson : Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         val binding : MovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.movie_detail)

        val movieId = intent.getIntExtra(IntentKeys.MOVIE_ID, -1)
        if(movieId != -1)
        {
            viewModel.loadMovieDetail(movieId)
            viewModel.movieDetail.observe(this, Observer
            {
                binding.movie = it

            })
        }


    }




}