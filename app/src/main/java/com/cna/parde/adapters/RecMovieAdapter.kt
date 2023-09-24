package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.RecMovie

class RecMovieAdapter(private val clickListener: RecMovieClickListener) :
    RecyclerView.Adapter<RecMovieAdapter.RecMovieViewHolder>() {

    private val movies = mutableListOf<RecMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_detail_cast, parent, false)
        return RecMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onRecMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<RecMovie>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class RecMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: RecMovie) {

            Glide.with(itemView.context)
                .load("$imageUrl${movie.poster_path}")
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface RecMovieClickListener {
        fun onRecMovieClick(movie: RecMovie)
    }
}