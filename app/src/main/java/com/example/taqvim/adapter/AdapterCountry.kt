package com.example.taqvim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taqvim.R
import com.example.taqvim.models.DataCountry
import kotlinx.android.synthetic.main.item_country.view.*

class AdapterCountry(val data: List<DataCountry>) : RecyclerView.Adapter<AdapterCountry.ViewHolder>(){

    private var listener: ((DataCountry,Int) -> Unit)? = null

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            image.setImageResource(data[position].imageId)
            txt_country.text = data[position].countryName
        }
        holder.itemView.card_view.setOnClickListener {
            listener?.invoke(data[position],position)
//            Toast.makeText(holder.itemView.context, "click adapter", Toast.LENGTH_SHORT).show()
        }

    }
    fun setOnClickListener(f: (DataCountry,Int)-> Unit){
        listener = f
    }
    override fun getItemCount(): Int  = data.size
}