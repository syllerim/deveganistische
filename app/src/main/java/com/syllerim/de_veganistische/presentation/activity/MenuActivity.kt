package com.syllerim.de_veganistische.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.TypeMenu
import com.syllerim.de_veganistische.data.TypesMenu
import com.syllerim.de_veganistische.presentation.fragment.MenuFragment
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), MenuFragment.OnMenuSelectedListener {

    companion object {
        const val EXTRA_TYPE_MENU_ID = "EXTRA_TYPE_MENU_ID"
        const val EXTRA_TABLE_ID = "EXTRA_TABLE_ID"

        fun intent(context: Context, typeMenu: Int, tableId: Int): Intent {
            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra(EXTRA_TYPE_MENU_ID, typeMenu)
            intent.putExtra(EXTRA_TABLE_ID, tableId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)
        setupToolBar(toolbar)

        if (findViewById<ViewGroup>(R.id.menu_fragment) != null) {

            if (supportFragmentManager.findFragmentById(R.id.menu_fragment) == null) {
                val tmId = intent.getIntExtra(EXTRA_TYPE_MENU_ID, 0)
                var typeMenu: TypeMenu = TypesMenu.typeMenu(tmId)
                tableNameTextView?.text = typeMenu.name

                val fragment = MenuFragment.newInstance(tmId)
                supportFragmentManager.beginTransaction()
                        .add(R.id.menu_fragment, fragment)
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
                        val tableId = intent.getIntExtra(TypeMenuActivity.EXTRA_TABLE_ID, -1)
                        startActivity(TypeMenuActivity.intent(applicationContext, tableId))
                    }
                })
    }

    override fun onMenuSelected(menuId: Int) {
        val tableId = intent.getIntExtra(EXTRA_TABLE_ID, -1)
        startActivity(MenuDetailActivity.intent(this, menuId, tableId))
    }
}