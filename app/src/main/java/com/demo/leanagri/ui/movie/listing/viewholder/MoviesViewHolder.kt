package com.demo.leanagri.ui.movie.listing.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.demo.leanagri.data.model.detail.MovieDetail
import com.demo.leanagri.databinding.ItemMoviesBinding

class MoviesViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

   fun onBind(item : MovieDetail, itemClick: View.OnClickListener)
   {

       binding.apply {
           movie = item
           clickListener = itemClick
           executePendingBindings()
       }

   }
}