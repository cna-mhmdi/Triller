package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cna.parde.R
import com.cna.parde.model.Search


class SearchAdapter(private val clickListener: SearchClickListener) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val movies = mutableListOf<Search>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onSearchClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<Search>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185"

        fun bind(movie: Search) {
            if (movie.media_type == "movie") {
                txtTitle.text = movie.title
            } else {
                txtTitle.text = movie.name
            }
            txtRate.text = movie.vote_average.toString()

            imgMoviePic.load("$imageUrl${movie.poster_path}")
        }
    }

    interface SearchClickListener {
        fun onSearchClick(movie: Search)
    }
}