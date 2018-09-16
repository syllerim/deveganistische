package com.syllerim.de_veganistische.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Dish

class MenuRecyclerViewAdapter(private val dishes: Array<Dish>): RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>()  {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_menu, parent, false)
        view.setOnClickListener {
            onClickListener?.onClick(it)
        }
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(dishes[position], position)
    }

    inner class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val menuImage = itemView.findViewById<ImageView?>(R.id.menu_image)
        val menuNameText = itemView.findViewById<TextView?>(R.id.menu_name)
        val menuPrice = itemView.findViewById<TextView?>(R.id.menu_price)
        val menuAllergens = itemView.findViewById<TextView?>(R.id.menu_allergens)

        fun bind(dish: Dish, position: Int) {

            var menu = dishes[position]

            // Update view with the model
            menuImage?.setImageResource(menu.icon)
            menuNameText?.text = menu.toString()
            menuPrice?.text = menu.price.toString() + "€"
            menuAllergens?.text = menu.allergens.joinToString(prefix = "[ ", postfix = " ]", separator = ",")
        }
    }
}