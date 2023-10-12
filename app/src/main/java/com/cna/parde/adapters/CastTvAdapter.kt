package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.CastTv


class CastTvAdapter(private val clickListener: CastTvClickListener) :
    RecyclerView.Adapter<CastTvAdapter.CastTvViewHolder>() {

    private val tvs = mutableListOf<CastTv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastTvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_detail_cast, parent, false)
        return CastTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastTvViewHolder, position: Int) {
        val tv = tvs[position]
        holder.bind(tv)
        holder.itemView.setOnClickListener { clickListener.onCastTvClick(tv) }
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    fun addMovies(tvList: List<CastTv>) {
        this.tvs.clear()
        this.tvs.addAll(tvList)
        notifyDataSetChanged()
    }


    inner class CastTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(cast: CastTv) {

            Glide.with(itemView.context)
                .load("$imageUrl${cast.profile_path}")
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface CastTvClickListener {
        fun onCastTvClick(tv: CastTv)
    }
}