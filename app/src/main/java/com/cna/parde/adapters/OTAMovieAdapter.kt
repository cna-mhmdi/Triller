package com.cna.parde.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cna.parde.R

class OTAMovieAdapter(private var names: Array<String>,private val rates : Array<String>)
    :RecyclerView.Adapter<OTAMovieAdapter.OTAMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OTAMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item,parent,false)
        return OTAMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: OTAMovieViewHolder, position: Int) {
        val btnNames = names[position]
        val btnRates = rates[position]
        holder.bind(btnNames,btnRates)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    fun updateData(newNames: Array<String>){
        names = newNames
        notifyDataSetChanged()
    }


    inner class OTAMovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitleMovie)
        private val txtRate: TextView = itemView.findViewById(R.id.txtRateMovie)

        fun bind(names: String,rates: String){
            txtTitle.text = names
            txtRate.text = rates
        }
    }
}