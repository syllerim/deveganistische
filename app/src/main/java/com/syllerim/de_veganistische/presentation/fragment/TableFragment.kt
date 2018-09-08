package com.syllerim.de_veganistische.presentation.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import com.syllerim.de_veganistische.data.Table
import com.syllerim.de_veganistische.data.Tables
import com.syllerim.de_veganistische.R
import android.support.v7.widget.RecyclerView
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.syllerim.de_veganistische.presentation.adapter.TableRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_table_list.*

class TableFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = TableFragment()
    }

    private var onTableSelectedListener: OnTableSelectedListener? = null

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.table_list)
        list.layoutManager = GridLayoutManager(context, 2)
        list
    }

    var tableData: List<Table>? = null
        set(value) {
            field = value
            if (value != null) {
                val adapter = TableRecyclerViewAdapter(value)
                table_list.adapter = adapter
                setRecyclerViewClickListener()
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
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        table_list.layoutManager = GridLayoutManager(activity, 2)
        table_list.itemAnimator = DefaultItemAnimator()
        tableData = Tables.allItems
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && tableData != null) {
            updateTableView()
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
        if (activity is OnTableSelectedListener) {
            onTableSelectedListener = activity
        }
        else {
            onTableSelectedListener = null
        }
    }

    override fun onDetach() {
        super.onDetach()

        onTableSelectedListener = null
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

    private fun setRecyclerViewClickListener() {
        val adapter = table_list?.adapter as? TableRecyclerViewAdapter
        adapter?.onClickListener = View.OnClickListener {
            val tableIndex = table_list.getChildAdapterPosition(it)
            onTableSelectedListener?.onTableSelected(tableIndex)
        }
    }

    fun updateTableView() {
        table_list?.adapter = TableRecyclerViewAdapter(tableData!!)
        setRecyclerViewClickListener()
    }

    interface OnTableSelectedListener {
        fun onTableSelected(position: Int)
    }
}