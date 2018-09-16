package com.syllerim.de_veganistische.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Table
import com.syllerim.de_veganistische.data.TypeMenu
import com.syllerim.de_veganistische.data.TypesMenu

class TypeMenuRecyclerViewAdapter(private val typesMenu: List<TypeMenu>): RecyclerView.Adapter<TypeMenuRecyclerViewAdapter.TypeMenuViewHolder>()  {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_type_menu, parent, false)
        view.setOnClickListener {
            onClickListener?.onClick(it)
        }
        return TypeMenuViewHolder(view)
    }

    override fun getItemCount(): Int = typesMenu.size

    override fun onBindViewHolder(holder: TypeMenuViewHolder, position: Int) {
        holder.bindTypeMenu(typesMenu[position], position)
    }

    inner class TypeMenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val typeMenuText = itemView.findViewById<TextView?>(R.id.type_menu_name)
        val typeMenuImage = itemView.findViewById<ImageView?>(R.id.type_menu_image)

        fun bindTypeMenu(typeMenu: TypeMenu, position: Int) {

            var typeMenu = TypesMenu.allItems[position]

            // Update view with the model
            typeMenuText?.text = typeMenu.name
            typeMenuImage?.setImageResource(typeMenu.icon)
        }
    }
}