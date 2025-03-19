package dk.itu.garbagev7_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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

        val listItemViewModel = ViewModelProvider(requireActivity())[ListItemsVM::class.java]

        val listThings: RecyclerView = v.findViewById(R.id.list_items_rv)
        listThings.layoutManager = LinearLayoutManager(activity)
        val mAdapter = ItemAdapter(listItemViewModel)

        activity?.let { fragmentActivity ->
            listItemViewModel.onListItemsTextViewDisplay(
                listThings,
                fragmentActivity
            )
        }

        return v
    }

    private inner class ItemHolder (itemView: View, deleteItemVM: DeleteItemVM) :
        RecyclerView.ViewHolder(itemView) {
        private val mWhatTextView: TextView = itemView.findViewById(R.id.item_what)
        private val mWhereTextView: TextView = itemView.findViewById(R.id.item_where)
        private val mNumberView: TextView = itemView.findViewById(R.id.item_number)
        private val viewModel = deleteItemVM

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: String, position: Int) {
            mNumberView.text = " $position "
            mWhatTextView.text = item.what // item.what does not work because we do not have item class or object
            mWhereTextView.text = item.where
            }
        override fun onClick(v: View) {
            val what = (v.findViewById<View>(R.id.item_what) as TextView).text.
            viewModel.onDeleteItemButtonClick(what, activity)
            }
            viewModel.onDeleteItemButtonClicked

        }


    }

    private inner class ItemAdapter(listItemViewModel:ListItemsVM) :
        RecyclerView.Adapter<ItemHolder>() {
            private val viewModel = listItemViewModel

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val layoutInflater = LayoutInflater.from(activity)
            val v = layoutInflater.inflate(R.layout.one_row_item, parent, false)
            return ItemHolder(v, viewModel)
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val item = viewModel.uiState.value!!.listItemVM[position]
            holder.bind(item, position)
        }

        override fun getItemCount(): Int {
            return viewModel.uiState.value!!.listItemVMSize
        }



}

}