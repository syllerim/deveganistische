package com.syllerim.de_veganistische.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Dishes

class TableDetailRecyclerViewAdapter(private val dishes: MutableList<Int>): RecyclerView.Adapter<TableDetailRecyclerViewAdapter.TableDetailViewHolder>()  {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableDetailRecyclerViewAdapter.TableDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_table_detail, parent, false)

//        val deleteButton = view.findViewById<ImageView?>(R.id.crossButton)
        view?.setOnClickListener {
            onClickListener?.onClick(it)
        }

        return TableDetailViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: TableDetailRecyclerViewAdapter.TableDetailViewHolder, position: Int) {
        holder.bindDishWith(dishes[position])
    }

    inner class TableDetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val orderImage = itemView.findViewById<ImageView?>(R.id.imageView)
        val orderName = itemView.findViewById<TextView?>(R.id.orderName)
        val orderPrice = itemView.findViewById<TextView?>(R.id.orderPrice)

        fun bindDishWith(id: Int) {
            var dish = Dishes.dish(id)

            // Update view with the model
            orderImage?.setImageResource(dish.icon)
            orderName?.text = dish.name
            orderPrice?.text = dish.price.toString() + "€"

        }
    }


}