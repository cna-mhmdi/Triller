package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.DisplayableItem

class HomeAdapter<T : DisplayableItem>(private val clickListener: HomeClickListener<T>) :
    RecyclerView.Adapter<HomeAdapter<T>.ViewHolder>() {

    private val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { clickListener.onClick(item) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addMovies(itemList: List<T>) {
        this.items.clear()
        this.items.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w500"

        fun bind(item: T) {

            if (item.isMovie){
                txtTitle.text = item.movieTitle
            }else{
                txtTitle.text = item.movieName
            }

            txtRate.text = item.voteAverage.toString()

            Glide.with(itemView.context)
                .load("$imageUrl${item.posterPath}")
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imgMoviePic)
        }
    }

    interface HomeClickListener<T> {
        fun onClick(movie: T)
    }
}
