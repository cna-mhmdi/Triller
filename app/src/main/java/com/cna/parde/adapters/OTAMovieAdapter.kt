package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.PopularMovie

class OTAMovieAdapter(private val clickListener: OTAMovieClickListener)
    :RecyclerView.Adapter<OTAMovieAdapter.OTAMovieViewHolder>() {

    private val movies = mutableListOf<PopularMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OTAMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item,parent,false)
        return OTAMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: OTAMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onOTAMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<PopularMovie>){
        movies.addAll(movieList)
        notifyItemRangeInserted(0,movieList.size)
    }


    inner class OTAMovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy {itemView.findViewById(R.id.txtTitleMovie)}
        private val txtRate: TextView by lazy {itemView.findViewById(R.id.txtRateMovie)}
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: PopularMovie){
            txtTitle.text = movie.title
            txtRate.text = movie.vote_average.toString()

            Glide.with(itemView.context)
                .load("$imageUrl${movie.poster_path}")
                .placeholder(R.drawable.star)
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface OTAMovieClickListener {
        fun onOTAMovieClick(movie: PopularMovie)
    }
}