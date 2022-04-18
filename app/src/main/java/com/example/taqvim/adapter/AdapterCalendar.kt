package com.example.taqvim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taqvim.R
import com.example.taqvim.models.DataCalendar
import kotlinx.android.synthetic.main.item_calendar.view.*

class AdapterCalendar(val data: List<DataCalendar>) : RecyclerView.Adapter<AdapterCalendar.ViewHoler>()  {

    class ViewHoler(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler  =
        ViewHoler(LayoutInflater.from(parent.context).inflate(R.layout.item_calendar,parent,false))

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        holder.itemView.apply {
            time.text = data[position].date
            tv_saxarlik_time.text = data[position].timeSaharlik
            tv_iftrolik_time.text = data[position].timeIftorlik
        }
    }

    override fun getItemCount(): Int = data.size
}