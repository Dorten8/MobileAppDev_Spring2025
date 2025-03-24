package dk.itu.garbagev6_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ListItemsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(R.layout.fragment_list_items, container, false)

        val viewModel = ViewModelProvider(requireActivity())[ListItemsVM::class.java]

        val listThings: TextView = v.findViewById(R.id.list_items_tw)

        /*
        activity?.let { fragmentActivity ->
            viewModel.onListItemsTextViewDisplay(
                listThings,
                fragmentActivity
            )
        }
         */

        viewModel.uiState.observe(viewLifecycleOwner){
        uiState -> listThings.text = "Garbage sorting list: " + uiState.listItems
        }

        return v
}

}