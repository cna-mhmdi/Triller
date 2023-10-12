package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.RecTv


class RecTvAdapter(private val clickListener: RecTvClickListener) :
    RecyclerView.Adapter<RecTvAdapter.RecTvViewHolder>() {

    private val movies = mutableListOf<RecTv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecTvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_detail_cast, parent, false)
        return RecTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecTvViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onRecTvClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<RecTv>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class RecTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(tv: RecTv) {

            Glide.with(itemView.context)
                .load("$imageUrl${tv.poster_path}")
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface RecTvClickListener {
        fun onRecTvClick(tv: RecTv)
    }
}