package com.syllerim.de_veganistische.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Table

class TableRecyclerViewAdapter(private val tables: List<Table>): RecyclerView.Adapter<TableRecyclerViewAdapter.TableViewHolder>()  {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_table, parent, false)
        view.setOnClickListener {
            onClickListener?.onClick(it)
        }
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int = tables.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bindTable(tables[position], position)
    }

    inner class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tableNameText = itemView.findViewById<TextView?>(R.id.table_name)

        fun bindTable(table: Table, position: Int) {
            // Update view with the model
            tableNameText?.text = table.name
        }
    }
}