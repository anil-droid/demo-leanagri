package com.demo.leanagri.ui.movie.listing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.leanagri.data.model.detail.MovieDetail
import com.demo.leanagri.databinding.ItemMoviesBinding
import com.demo.leanagri.ui.movie.listing.viewholder.MoviesViewHolder
import com.demo.leanagri.utils.callbacks.IItemClickCallback

class MoviesListAdapter(
    private var news: List<MovieDetail?>,
    private val itemClick: IItemClickCallback<MovieDetail>
) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMoviesBinding.inflate(layoutInflater, parent, false)
        return MoviesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        val article = news[position]
        article?.let { artInPos ->

            holder.apply {
                onBind(artInPos) {
                    itemClick.onItemClick(artInPos)
                }
            }
        }
    }

    override fun getItemCount() = news.size
    fun updateData(newsList: List<MovieDetail>) {
        news += newsList
        notifyDataSetChanged()
    }

    fun clearData() {
        news = emptyList()
        notifyDataSetChanged()
    }
}