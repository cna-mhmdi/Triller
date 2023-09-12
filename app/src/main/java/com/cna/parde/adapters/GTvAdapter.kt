package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cna.parde.R
import com.cna.parde.model.GMovie
import com.cna.parde.model.GTv

class GTvAdapter(private val clickListener: GTvClickListener) :
    RecyclerView.Adapter<GTvAdapter.GTvViewHolder>() {

    private val movies = mutableListOf<GTv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GTvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return GTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: GTvViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onGTvClick(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movieList: List<GTv>) {
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }


    inner class GTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(tv: GTv) {
            txtTitle.text = tv.name
            txtRate.text = tv.vote_average.toString()

            imgMoviePic.load("$imageUrl${tv.poster_path}")
        }
    }

    interface GTvClickListener {
        fun onGTvClick(tv: GTv)
    }
}