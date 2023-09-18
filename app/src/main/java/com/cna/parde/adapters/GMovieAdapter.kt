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
import com.cna.parde.model.GMovie
import com.cna.parde.model.NPMovie


class GMovieAdapter(private val clickListener: GMovieClickListener) :
    RecyclerView.Adapter<GMovieAdapter.GMovieViewHolder>() {

    private val movies = mutableListOf<GMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return GMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: GMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onGMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<GMovie>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class GMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: GMovie) {
            txtTitle.text = movie.title
            txtRate.text = movie.vote_average.toString()

            Glide.with(itemView.context)
                .load("$imageUrl${movie.poster_path}")
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface GMovieClickListener {
        fun onGMovieClick(movie: GMovie)
    }
}