package com.cna.parde.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R

class OTAMovieAdapter(private var movieNames: Array<String>):RecyclerView.Adapter<OTAMovieAdapter.OTAMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OTAMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_ota_movie_recyclerview,parent,false)
        return OTAMovieViewHolder(view)

    }

    override fun onBindViewHolder(holder: OTAMovieViewHolder, position: Int) {
        val movies = movieNames[position]
        holder.bind(movies)

    }

    override fun getItemCount(): Int {
        return movieNames.size
    }


    inner class OTAMovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val txtTitleMovie: TextView = itemView.findViewById(R.id.txtTitleMovie)
        private val imgMoviePic : ImageView = itemView.findViewById(R.id.imgMoviePic)

        @SuppressLint("ResourceType")
        fun bind(movie: String){
            txtTitleMovie.text = movie

            val imgURL = "https://media.gq.com/photos/645956c367d4264086a5d77f/16:9/w_1920,c_limit/Screen%20Shot%202023-05-08%20at%204.07.48%20PM.png"
            Glide.with(itemView)
                .load(imgURL)
                .placeholder(R.raw.loading)
                .into(imgMoviePic)
        }
    }
}