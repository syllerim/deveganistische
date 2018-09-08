package com.syllerim.de_veganistische.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.presentation.fragment.TableFragment

class TableActivity : AppCompatActivity(), TableFragment.OnTableSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_table)

        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {

            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {

                val fragment = TableFragment.newInstance()

                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }
    }

    override fun onTableSelected(position: Int) {
//        startActivity(TableDetailActivity.intent(this, position))
    }

}