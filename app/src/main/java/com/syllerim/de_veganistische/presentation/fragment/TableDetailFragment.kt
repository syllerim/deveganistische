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
import com.syllerim.de_veganistische.data.Table
import com.syllerim.de_veganistische.data.Tables
import com.syllerim.de_veganistische.presentation.activity.TableDetailActivity
import com.syllerim.de_veganistische.presentation.adapter.TableDetailRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_table_detail.*
import kotlinx.android.synthetic.main.fragment_table_list.*

class TableDetailFragment: Fragment() {

    companion object {
        val ARG_TABLE_INDEX = "ARG_TABLE_INDEX"

        fun newInstance(tableIndex: Int): TableDetailFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE_INDEX, tableIndex)
            val fragment = TableDetailFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.table_detail_order_list)
        list.layoutManager = GridLayoutManager(context, 1)
        list
    }

    var orders: Array<Int>? = null
        set(value) {
            field = value
            if (value != null) {
                var dishes = value
                val adapter = TableDetailRecyclerViewAdapter(dishes)
                table_detail_order_list.adapter = adapter
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.activity_menu, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        table_detail_order_list.layoutManager = GridLayoutManager(activity, 1)
        table_detail_order_list.itemAnimator = DefaultItemAnimator()

        var tableIndex = arguments?.getInt(ARG_TABLE_INDEX, 0)!!

        orders = Tables.allDishesForTable(tableIndex)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu -> {
                // Open Menu View and Store the current Table Selected
//                val dialog = SettingsDialog.newInstance(getTemperatureUnits(activity!!))
//                dialog.setTargetFragment(this, REQUEST_SETTINGS)
//                dialog.show(fragmentManager, null)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}