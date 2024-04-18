package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.DisplayableTvItems
import com.cna.parde.model.OTATv

class TvAdapter<T: DisplayableTvItems>(private val clickListener: TvClickListener<T>) :
    RecyclerView.Adapter<TvAdapter<T>.TvViewHolder>() {

    private val tvs = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = tvs[position]
        holder.bind(tv)
        holder.itemView.setOnClickListener { clickListener.onTvClick(tv) }
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    fun addMovies(tvList: List<T>) {
        this.tvs.clear()
        this.tvs.addAll(tvList)
        notifyDataSetChanged()
    }

    inner class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(tv: T) {
            txtTitle.text = tv.tvTitle
            txtRate.text = tv.voteAverage.toString()

            Glide.with(itemView.context)
                .load("$imageUrl${tv.posterPath}")
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface TvClickListener<T> {
        fun onTvClick(tv: T)
    }
}