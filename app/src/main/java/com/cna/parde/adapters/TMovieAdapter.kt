package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cna.parde.R
import com.cna.parde.model.TMovie


class TMovieAdapter(private val clickListener: TMovieClickListener) :
    RecyclerView.Adapter<TMovieAdapter.TMovieViewHolder>() {

    private val movies = mutableListOf<TMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return TMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: TMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onTMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<TMovie>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class TMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: TMovie) {
            txtTitle.text = movie.title
            txtRate.text = movie.vote_average.toString()

            imgMoviePic.load("$imageUrl${movie.poster_path}")
        }
    }

    interface TMovieClickListener {
        fun onTMovieClick(movie: TMovie)
    }
}