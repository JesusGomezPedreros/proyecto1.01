package com.example.proyecto101

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val scheduleList:ArrayList<ScheduleFirebase>): RecyclerView.Adapter<MyAdapter.myViewHolder>() {


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gatherer_items,parent,false)
		return myViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: myViewHolder, position: Int) {
		val currentitem=scheduleList[position]
		holder.name.text = currentitem.name
		holder.number.text = currentitem.numberPhone
		holder.email.text = currentitem.email
		holder.address.text=currentitem.numberAddress
		holder.material.text=currentitem.materials
		holder.date.text = currentitem.date

	}

	override fun getItemCount(): Int {

		return scheduleList.size
	}

	class	myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
			val name : TextView = itemView.findViewById(R.id.tvName)
			val number:TextView=itemView.findViewById(R.id.tvNumber)
			val email: TextView=itemView.findViewById(R.id.tvEmail)
			val address:TextView=itemView.findViewById(R.id.tvAddress)
			val material:TextView=itemView.findViewById(R.id.tvMaterial)
			val date:TextView=itemView.findViewById(R.id.tvDate)
	}
}