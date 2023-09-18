package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv


class TTvAdapter(private val clickListener: TTvClickListener) :
    RecyclerView.Adapter<TTvAdapter.TTvViewHolder>() {

    private val tvs = mutableListOf<TTv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TTvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return TTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TTvViewHolder, position: Int) {
        val tv = tvs[position]
        holder.bind(tv)
        holder.itemView.setOnClickListener { clickListener.onTTvClick(tv) }
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    fun addMovies(tvList: List<TTv>) {
        this.tvs.clear()
        this.tvs.addAll(tvList)
        notifyDataSetChanged()
    }

    inner class TTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(tv: TTv) {
            txtTitle.text = tv.name
            txtRate.text = tv.vote_average.toString()

            Glide.with(itemView.context)
                .load("$imageUrl${tv.poster_path}")
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface TTvClickListener {
        fun onTTvClick(tv: TTv)
    }
}