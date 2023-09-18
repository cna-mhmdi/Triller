package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.UCMovie

class UCMovieAdapter(private val clickListener: UCMovieClickListener) :
    RecyclerView.Adapter<UCMovieAdapter.UCMovieViewHolder>() {

    private val movies = mutableListOf<UCMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UCMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return UCMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: UCMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onUCMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<UCMovie>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class UCMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: UCMovie) {
            txtTitle.text = movie.title
            txtRate.text = movie.vote_average.toString()

            Glide.with(itemView.context)
                .load("$imageUrl${movie.poster_path}")
                .fitCenter()
                .into(imgMoviePic)

        }
    }

    interface UCMovieClickListener {
        fun onUCMovieClick(movie: UCMovie)
    }
}