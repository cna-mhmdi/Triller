package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.DisplayItem

class RecAdapter<T: DisplayItem>(private val clickListener: RecClickListener<T>) :
    RecyclerView.Adapter<RecAdapter<T>.ViewHolder>() {

    private val movies = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_detail_cast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onRecClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<T>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: T) {

            Glide.with(itemView.context)
                .load("$imageUrl${movie.profilePath}")
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface RecClickListener<T> {
        fun onRecClick(movie: T)
    }
}