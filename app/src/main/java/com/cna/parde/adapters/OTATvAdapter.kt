package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R
import com.cna.parde.model.OTATv

class OTATvAdapter(private val clickListener: OTATvClickListener) :
    RecyclerView.Adapter<OTATvAdapter.OTATvViewHolder>() {

    private val tvs = mutableListOf<OTATv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OTATvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return OTATvViewHolder(view)
    }

    override fun onBindViewHolder(holder: OTATvViewHolder, position: Int) {
        val tv = tvs[position]
        holder.bind(tv)
        holder.itemView.setOnClickListener { clickListener.onOTATvClick(tv) }
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    fun addMovies(tvList: List<OTATv>) {
        this.tvs.clear()
        this.tvs.addAll(tvList)
        notifyDataSetChanged()
    }

    inner class OTATvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView by lazy { itemView.findViewById(R.id.txtTitleMovie) }
        private val txtRate: TextView by lazy { itemView.findViewById(R.id.txtRateMovie) }
        private val imgMoviePic: ImageView by lazy { itemView.findViewById(R.id.imgMoviePic) }
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(tv: OTATv) {
            txtTitle.text = tv.name
            txtRate.text = tv.vote_average.toString()

            Glide.with(itemView.context)
                .load("$imageUrl${tv.poster_path}")
                .fitCenter()
                .into(imgMoviePic)
        }
    }

    interface OTATvClickListener {
        fun onOTATvClick(tv: OTATv)
    }
}