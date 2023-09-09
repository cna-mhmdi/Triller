package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cna.parde.R
import com.cna.parde.model.POPTv

class POPTvAdapter(private val clickListener: POPTvClickListener) :
    RecyclerView.Adapter<POPTvAdapter.POPTvViewHolder>() {

    private val tvs = mutableListOf<POPTv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): POPTvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return POPTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: POPTvViewHolder, position: Int) {
        val tv = tvs[position]
        holder.bind(tv)
        holder.itemView.setOnClickListener { clickListener.onPOPTvClick(tv) }
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    fun addMovies(tvList: List<POPTv>) {
        this.tvs.clear()
        this.tvs.addAll(tvList)
        notifyDataSetChanged()
    }

    inner class POPTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(tv: POPTv) {
            txtTitle.text = tv.original_name
            txtRate.text = tv.vote_average.toString()

            imgMoviePic.load("$imageUrl${tv.poster_path}")
        }
    }

    interface POPTvClickListener {
        fun onPOPTvClick(tv: POPTv)
    }
}