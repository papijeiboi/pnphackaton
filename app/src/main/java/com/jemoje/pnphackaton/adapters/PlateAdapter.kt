package com.jemoje.pnphackaton.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jemoje.pnphackaton.R
import com.jemoje.pnphackaton.activities.SearchPlateNumberActivity
import com.jemoje.pnphackaton.model.PlateData
import com.jemoje.pnphackaton.model.PlateResponse

class PlateAdapter constructor(listString : MutableList<PlateData>, context : SearchPlateNumberActivity):RecyclerView.Adapter<PlateAdapter.ItemViewHolder>() {

    val listPlateNumbers: MutableList<PlateData> = listString
    val searchPlateNumberActivity: SearchPlateNumberActivity = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_plate_number_card, parent, false)


        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listPlateNumbers.size
    }

    override fun onBindViewHolder(holder:ItemViewHolder, position: Int) {
        holder.plateNumber.text = listPlateNumbers[position].plateNumber
        holder.name.text = "${listPlateNumbers[position].user!!.firstName} ${listPlateNumbers[position].user!!.lastName} "

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var plateNumber: TextView
        var name: TextView

        init {
            plateNumber = itemView.findViewById(R.id.plate_number)
            name = itemView.findViewById(R.id.plate_owner_name)
        }

    }

}