package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.R
import com.cna.parde.model.Movie

class OTAMovieAdapter(private val clickListener: MovieClickListener)
    :RecyclerView.Adapter<OTAMovieAdapter.OTAMovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OTAMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item,parent,false)
        return OTAMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: OTAMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onMovieClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<Movie>){
        movies.addAll(movieList)
        notifyItemRangeInserted(0,movieList.size)
    }


    inner class OTAMovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy {itemView.findViewById(R.id.txtTitleMovie)}
        private val txtRate: TextView by lazy {itemView.findViewById(R.id.txtRateMovie)}

        fun bind(movie: Movie){
            txtTitle.text = movie.title
            txtRate.text = movie.vote_average.toString()
        }
    }

    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}