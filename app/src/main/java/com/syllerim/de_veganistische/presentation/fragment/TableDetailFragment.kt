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
import com.syllerim.de_veganistische.data.Dish
import com.syllerim.de_veganistische.data.Tables
import com.syllerim.de_veganistische.presentation.adapter.TableDetailRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_table_detail.*

class TableDetailFragment: Fragment() {

    companion object {
        val ARG_TABLE_ID = "ARG_TABLE_ID"

        fun newInstance(tableIndex: Int): TableDetailFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE_ID, tableIndex)
            val fragment = TableDetailFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    private var onMenuSelectedListener: OnMenuSelectedListener? = null

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.table_detail_order_list)
        list.layoutManager = GridLayoutManager(context, 1)
        list
    }

    var orders: MutableList<Int>? = null
        set(value) {
            field = value
            if (value != null) {
                var dishes = value
                val adapter = TableDetailRecyclerViewAdapter(dishes)
                table_detail_order_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        table_detail_order_list.layoutManager = GridLayoutManager(activity, 1)
        table_detail_order_list.itemAnimator = DefaultItemAnimator()

        var tableId = arguments?.getInt(ARG_TABLE_ID, 0)!!

        orders = Tables.allDishesForTable(tableId)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && orders != null) {
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
        if (activity is TableDetailFragment.OnMenuSelectedListener) {
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
        val adapter = table_detail_order_list?.adapter as? TableDetailRecyclerViewAdapter
        adapter?.onClickListener = View.OnClickListener {
            var position: Int = table_detail_order_list.getChildAdapterPosition(it)
            onMenuSelectedListener?.onMenuSelectedListener(position)
        }
    }

    private fun updateTypesMenuView() {
        table_detail_order_list?.adapter = TableDetailRecyclerViewAdapter(orders!!)
        setRecyclerViewClickListener()
    }

    interface OnMenuSelectedListener {
        fun onMenuSelectedListener(position: Int)
    }

}