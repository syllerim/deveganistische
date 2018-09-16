package com.syllerim.de_veganistische.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Dish
import com.syllerim.de_veganistische.data.Dishes
import kotlinx.android.synthetic.main.activity_menu_detail.*

class MenuDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MENU_ID = "EXTRA_MENU_ID"

        fun intent(context: Context, menuId: Int): Intent {
            val intent = Intent(context, MenuDetailActivity::class.java)
            intent.putExtra(EXTRA_MENU_ID, menuId)
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
    }

    private fun setupToolBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(v: View) {
                        //TODO Mirar esto bien oprque tengo que volver a la lista de menus del actual
                        startActivity(Intent(applicationContext, MenuActivity::class.java))
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