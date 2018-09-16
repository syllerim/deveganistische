package com.syllerim.de_veganistische.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Dishes
import com.syllerim.de_veganistische.data.Tables
import com.syllerim.de_veganistische.presentation.fragment.TableDetailFragment

import kotlinx.android.synthetic.main.activity_table_detail.*

class TableDetailActivity : AppCompatActivity(), TableDetailFragment.OnMenuSelectedListener{

    companion object {
        val EXTRA_TABLE_ID = "EXTRA_TABLE_ID"

        fun intent(context: Context, tableId: Int): Intent {
            val intent = Intent(context, TableDetailActivity::class.java)
            intent.putExtra(EXTRA_TABLE_ID, tableId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_table_detail)
        setupToolBar(toolbar)
        setupFragment()

        addButton.setOnClickListener {
            val tableId = intent.getIntExtra(EXTRA_TABLE_ID, 0)
            startActivity(TypeMenuActivity.intent(this, tableId))
        }
    }

    override fun onMenuSelectedListener(position: Int) {
        var menuId = Dishes.allItems[position].id
        val tableId = intent.getIntExtra(MenuDetailActivity.EXTRA_TABLE_ID, -1)

        Tables.remove(menuId, tableId)

        var tables = Tables.allDishesForTable(4)

        finish()
        startActivity(intent)
    }

    private fun setupToolBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(v: View) {
                        startActivity(Intent(applicationContext, TableActivity::class.java))
                    }
                })
    }

    private fun setupFragment() {
        if (findViewById<ViewGroup>(R.id.table_detail_list_fragment) != null) {

            if (supportFragmentManager.findFragmentById(R.id.table_detail_list_fragment) == null) {

                val tableId = intent.getIntExtra(EXTRA_TABLE_ID, 0)
                val table = Tables.table(tableId)
                tableNameTextView?.text = table.name

                if (supportFragmentManager.findFragmentById(R.id.table_detail_list_fragment) == null) {
                    val fragment = TableDetailFragment.newInstance(tableId)
                    supportFragmentManager.beginTransaction()
                            .add(R.id.table_detail_list_fragment, fragment)
                            .commit()
                }
            }
        }
    }

}
