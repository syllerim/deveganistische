package com.syllerim.de_veganistische.presentation.activity

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.View.VISIBLE
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Dish
import com.syllerim.de_veganistische.data.Dishes
import com.syllerim.de_veganistische.data.Tables
import com.syllerim.de_veganistische.data.TypesMenu
import com.syllerim.de_veganistische.presentation.fragment.TableDetailFragment
import com.syllerim.de_veganistische.presentation.fragment.TypeMenuFragment
import kotlinx.android.synthetic.main.activity_menu_detail.*

class MenuDetailActivity : AppCompatActivity()  {


    companion object {
        const val EXTRA_MENU_ID = "EXTRA_MENU_ID"
        const val EXTRA_TABLE_ID = "EXTRA_TABLE_ID"

        fun intent(context: Context, menuId: Int, tableId: Int): Intent {
            val intent = Intent(context, MenuDetailActivity::class.java)
            intent.putExtra(EXTRA_MENU_ID, menuId)
            intent.putExtra(EXTRA_TABLE_ID, tableId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu_detail)
        setupToolBar(toolbar)

        val menuId = intent.getIntExtra(EXTRA_MENU_ID, 0)
        var menu = Dishes.allItems.filter { it.id == menuId }.first()
        bind(menu)

        val tableId = intent.getIntExtra(EXTRA_TABLE_ID, -1)
        if (tableId > 0) {
            addToTableButton.setOnClickListener {
                Tables.add(menuId, tableId)
                AlertDialog.Builder(this)
                        .setTitle("Menu added")
                        .setMessage("The menu has been added to the table successfully")
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            startActivity(TableDetailActivity.intent(this, tableId))
                        }
                        .show()
            }
            addToTableButton.visibility = VISIBLE
        }
    }

    private fun setupToolBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(v: View) {

                        val menuId = intent.getIntExtra(EXTRA_MENU_ID, 0)
                        var menu = Dishes.allItems.filter { it.id == menuId }.first()

                        val tableId = intent.getIntExtra(EXTRA_TABLE_ID, -1)
                        startActivity(MenuActivity.intent(applicationContext, menu.typeMenu, tableId))
                    }
                })
    }

    private fun bind(menu: Dish) {
        menuName.text = menu.name
        menuIcon.setImageResource(menu.icon)
        menuDescription.text = menu.description
        menuIngredients.text = menu.ingredients.joinToString(prefix = "", postfix = "", separator = ", ")
        menuAllergens?.text = menu.allergens.joinToString(prefix = "[ ", postfix = " ]", separator = ", ")
        menuPrice.text = menu.price.toString() + " €."
    }

}