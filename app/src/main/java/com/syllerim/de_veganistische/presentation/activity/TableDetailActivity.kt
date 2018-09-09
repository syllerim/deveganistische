package com.syllerim.de_veganistische.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Tables
import com.syllerim.de_veganistische.presentation.fragment.TableDetailFragment

import kotlinx.android.synthetic.main.activity_table_detail.*

class TableDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TableDetailActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_table_detail)

        if (findViewById<ViewGroup>(R.id.table_detail_list_fragment) != null) {

            if (supportFragmentManager.findFragmentById(R.id.table_detail_list_fragment) == null) {

                val tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)
                //update interface

                val data = Tables.allItems[tableIndex]

                tableNameTextView?.text = data.name

                if (supportFragmentManager.findFragmentById(R.id.table_detail_list_fragment) == null) {
                    val fragment = TableDetailFragment.newInstance(tableIndex)
                    supportFragmentManager.beginTransaction()
                            .add(R.id.table_detail_list_fragment, fragment)
                            .commit()
                }
            }
        }
    }
}
