package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cna.parde.R
import com.cna.parde.model.TRTv

class TRTvAdapter(private val clickListener: TRTvClickListener) :
    RecyclerView.Adapter<TRTvAdapter.TRTvViewHolder>() {

    private val tvs = mutableListOf<TRTv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TRTvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return TRTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TRTvViewHolder, position: Int) {
        val tv = tvs[position]
        holder.bind(tv)
        holder.itemView.setOnClickListener { clickListener.onTRTvClick(tv) }
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    fun addMovies(tvList: List<TRTv>) {
        this.tvs.clear()
        this.tvs.addAll(tvList)
        notifyDataSetChanged()
    }

    inner class TRTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(tv: TRTv) {
            txtTitle.text = tv.original_name
            txtRate.text = tv.vote_average.toString()

            imgMoviePic.load("$imageUrl${tv.poster_path}")
        }
    }

    interface TRTvClickListener {
        fun onTRTvClick(tv: TRTv)
    }
}