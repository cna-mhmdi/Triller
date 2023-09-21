package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.Cast
import com.cna.parde.model.SimilarMovie

class SimilarMovieAdapter(private val clickListener: SimilarMovieClickListener) :
    RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    private val movies = mutableListOf<SimilarMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_detail_cast, parent, false)
        return SimilarMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onSimilarMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<SimilarMovie>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class SimilarMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: SimilarMovie) {

            Glide.with(itemView.context)
                .load("$imageUrl${movie.poster_path}")
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface SimilarMovieClickListener {
        fun onSimilarMovieClick(movie: SimilarMovie)
    }
}