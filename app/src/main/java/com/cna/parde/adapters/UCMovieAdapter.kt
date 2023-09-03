package com.cna.parde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.R

class UCMovieAdapter(private var names: Array<String>,private val rates : Array<String>)
    : RecyclerView.Adapter<UCMovieAdapter.UCMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UCMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item,parent,false)
        return UCMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: UCMovieViewHolder, position: Int) {
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

    inner class UCMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitleMovie)
        private val txtRate: TextView = itemView.findViewById(R.id.txtRateMovie)

        fun bind(names: String,rates: String){
            txtTitle.text = names
            txtRate.text = rates
        }
    }
}