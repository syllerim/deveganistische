package com.syllerim.de_veganistische.presentation.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.Dish
import com.syllerim.de_veganistische.data.Dishes
import com.syllerim.de_veganistische.data.TypesMenu
import com.syllerim.de_veganistische.presentation.activity.MenuActivity
import com.syllerim.de_veganistische.presentation.adapter.MenuRecyclerViewAdapter
import com.syllerim.de_veganistische.presentation.adapter.TableDetailRecyclerViewAdapter
import com.syllerim.de_veganistische.presentation.adapter.TypeMenuRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_menu_list.*

class MenuFragment : Fragment() {

    companion object {
        val ARG_TYPE_MENU_INDEX = "ARG_TYPE_MENU_INDEX"

        fun newInstance(typeMenuId: Int): MenuFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TYPE_MENU_INDEX, typeMenuId)
            val fragment = MenuFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    private var onMenuSelectedListener: OnMenuSelectedListener? = null

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.menu_list)
        list.layoutManager = GridLayoutManager(context, 1)
        list
    }

    var dishesData: Array<Dish>? = null
        set(value) {
            field = value
            if (value != null) {
                val adapter = MenuRecyclerViewAdapter(value)
                menu_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_list.layoutManager = GridLayoutManager(activity, 1)
        menu_list.itemAnimator = DefaultItemAnimator()


        var typeMenuId = arguments?.getInt(MenuFragment.ARG_TYPE_MENU_INDEX, 0)!!

        dishesData = Dishes.allDishesOf(typeMenuId).toTypedArray()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && dishesData != null) {
            updateTypesMenuView()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    private fun commonAttach(activity: Activity?) {
        if (activity is OnMenuSelectedListener) {
            onMenuSelectedListener = activity
        }
        else {
            onMenuSelectedListener = null
        }
    }

    override fun onDetach() {
        super.onDetach()

        onMenuSelectedListener = null
    }

    private fun setRecyclerViewClickListener() {
        val adapter = menu_list?.adapter as? MenuRecyclerViewAdapter
        adapter?.onClickListener = View.OnClickListener {
            val index = menu_list.getChildAdapterPosition(it)
            dishesData?.get(index)?.let {
                onMenuSelectedListener?.onMenuSelected(it.id)
            }
        }
    }

    fun updateTypesMenuView() {
        menu_list?.adapter = MenuRecyclerViewAdapter(dishesData!!)
        setRecyclerViewClickListener()
    }

    interface OnMenuSelectedListener {
        fun onMenuSelected(menuId: Int)
    }
}