package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.Cast

class CastMovieAdapter(private val clickListener: CastMovieClickListener) :
    RecyclerView.Adapter<CastMovieAdapter.CastMovieViewHolder>() {

    private val movies = mutableListOf<Cast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_detail_cast, parent, false)
        return CastMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onCastMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<Cast>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class CastMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(cast: Cast) {

            Glide.with(itemView.context)
                .load("$imageUrl${cast.profile_path}")
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface CastMovieClickListener {
        fun onCastMovieClick(cast: Cast)
    }
}