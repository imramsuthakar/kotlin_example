package com.example.recyclerviewkotlin;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recyclerviewkotlin.models.SampleData
import kotlinx.android.synthetic.main.item_list_inflate.view.*

class ListAdapter(private val list: List<SampleData>) : RecyclerView.Adapter<ListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_inflate, parent, false)
        return CustomViewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = list.get(position)

        holder.rowView.title.text = item.title
        holder.rowView.body.text = item.body

        holder.item = item


    }

    class CustomViewHolder(val rowView: View, var item: SampleData? = null) : RecyclerView.ViewHolder(rowView) {

        init {
            rowView.body.setOnClickListener {
                println(item)
            }

        }

    }




}