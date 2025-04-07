package dk.itu.garbagev7_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListItemsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(R.layout.fragment_list_items, container, false)

        val viewModel = ViewModelProvider(requireActivity())[SharedItemsVM::class.java]

        // Set up recyclerview

        val listThings: RecyclerView = v.findViewById(R.id.list_items_rv)
        listThings.layoutManager = LinearLayoutManager(activity)

        val mAdapter = ItemAdapter(viewModel)
        listThings.adapter = mAdapter

        viewModel.uiState.observe(viewLifecycleOwner) { mAdapter.notifyDataSetChanged() }

        return v
    }

    private inner class ItemHolder (itemView: View, sharedItemsVM: SharedItemsVM) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private val mWhatTextView: TextView
        private val mWhereTextView: TextView
        private val mNumberView: TextView
        private val viewModel = sharedItemsVM

        init {
            mNumberView = itemView.findViewById(R.id.item_number)
            mWhatTextView = itemView.findViewById(R.id.item_what)
            mWhereTextView = itemView.findViewById(R.id.item_where)
            itemView.setOnClickListener(this)
        }

        fun bind(item: Item, position: Int) {
            mNumberView.text = " $position "
            mWhatTextView.text = item.what
            mWhereTextView.text = item.where
        }

        override fun onClick(v: View) {
            val what = (v.findViewById<View>(R.id.item_what) as TextView).text as String
            activity?.let { fragmentActivity ->
                viewModel.deleteItem(what, fragmentActivity)
            }
        }
    }

    private inner class ItemAdapter(sharedItemsVM: SharedItemsVM) : RecyclerView.Adapter<ItemHolder>() {

        private val viewModel = sharedItemsVM

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val layoutInflater = LayoutInflater.from(activity)
            val v = layoutInflater.inflate(R.layout.one_row_item, parent, false)
            return ItemHolder(v, viewModel)
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val item = viewModel.uiState.value!!.itemList[position]
            holder.bind(item, position)
        }

        override fun getItemCount(): Int {
            return viewModel.uiState.value!!.itemListSize
        }
    }
}
