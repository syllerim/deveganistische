package com.syllerim.de_veganistische.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Tables
import com.syllerim.de_veganistische.presentation.fragment.TableFragment
import kotlinx.android.synthetic.main.activity_table.*

class TableActivity : AppCompatActivity(), TableFragment.OnTableSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_table)
        setSupportActionBar(toolbar)

        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {

            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {

                val fragment = TableFragment.newInstance()

                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) : Boolean = when (item?.itemId) {
        R.id.menu -> {
            startActivity(TypeMenuActivity.intent(this, -1))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onTableSelected(position: Int) {
        val tableId = Tables.allItems[position].id
        startActivity(TableDetailActivity.intent(this, tableId))
    }


}