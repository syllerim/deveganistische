package com.syllerim.de_veganistische.presentation.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.syllerim.de_veganistische.R
import com.syllerim.de_veganistische.data.TypeMenu
import com.syllerim.de_veganistische.data.TypesMenu
import com.syllerim.de_veganistische.presentation.adapter.TypeMenuRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_type_menu_list.*

class TypeMenuFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = TypeMenuFragment()
    }

    private var onTypeMenuSelectedListener: OnTypeMenuSelectedListener? = null

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.type_menu_list)
        list.layoutManager = GridLayoutManager(context, 2)
        list
    }

    var typesMenuData: List<TypeMenu>? = null
        set(value) {
            field = value
            if (value != null) {
                val adapter = TypeMenuRecyclerViewAdapter(value)
                type_menu_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_type_menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type_menu_list.layoutManager = GridLayoutManager(activity, 2)
        type_menu_list.itemAnimator = DefaultItemAnimator()
        typesMenuData = TypesMenu.allItems
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && typesMenuData != null) {
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
        if (activity is OnTypeMenuSelectedListener) {
            onTypeMenuSelectedListener = activity
        }
        else {
            onTypeMenuSelectedListener = null
        }
    }

    override fun onDetach() {
        super.onDetach()

        onTypeMenuSelectedListener = null
    }

    private fun setRecyclerViewClickListener() {
        val adapter = type_menu_list?.adapter as? TypeMenuRecyclerViewAdapter
        adapter?.onClickListener = View.OnClickListener {
            val typeMenuIndex = type_menu_list.getChildAdapterPosition(it)
            onTypeMenuSelectedListener?.onTypeMenuSelected(typeMenuIndex)
        }
    }

    private fun updateTypesMenuView() {
        type_menu_list?.adapter = TypeMenuRecyclerViewAdapter(typesMenuData!!)
        setRecyclerViewClickListener()
    }

    interface OnTypeMenuSelectedListener {
        fun onTypeMenuSelected(position: Int)
    }
}