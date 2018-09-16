package com.syllerim.de_veganistische.presentation.activity

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.TypesMenu
import com.syllerim.de_veganistische.presentation.fragment.TypeMenuFragment
import kotlinx.android.synthetic.main.activity_type_menu.*

class TypeMenuActivity: AppCompatActivity(), TypeMenuFragment.OnTypeMenuSelectedListener {

    companion object {
        const val EXTRA_TABLE_ID = "EXTRA_TABLE_ID"

        fun intent(context: Context, tableId: Int): Intent {
            val intent = Intent(context, TypeMenuActivity::class.java)
            intent.putExtra(EXTRA_TABLE_ID, tableId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_type_menu)
        setupToolBar(toolbar)

        if (findViewById<ViewGroup>(R.id.type_menu_fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.type_menu_fragment) == null) {
                val fragment = TypeMenuFragment.newInstance()
                supportFragmentManager.beginTransaction()
                        .add(R.id.type_menu_fragment, fragment)
                        .commit()
            }
        }
    }

    private fun setupToolBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(v: View) {
                        val tableId = intent.getIntExtra(EXTRA_TABLE_ID, -1)
                        startActivity(TableDetailActivity.intent(applicationContext, tableId))
                    }
                })
    }

    override fun onTypeMenuSelected(position: Int) {
        val tableId = intent.getIntExtra(EXTRA_TABLE_ID, -1)
        startActivity(MenuActivity.intent(this, TypesMenu.allItems[position].id, tableId))
    }

}