package dk.itu.garbagev6_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddItemFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(R.layout.fragment_add_item, container, false)

        val viewModel = ViewModelProvider(requireActivity())[AddItemVM::class.java]

        val addItemButton: Button = v.findViewById(R.id.add_item_bt)

        val addItemWhatEditText: EditText = v.findViewById(R.id.add_item_what_et)
        val addItemWhereEditText: EditText = v.findViewById(R.id.add_item_where_et)

        addItemButton.setOnClickListener{
            activity?.let { fragmentActivity ->
                viewModel.onAddItemButtonClick(
                    addItemWhatEditText,
                    addItemWhereEditText,
                    fragmentActivity
                )
            }
        }
        return v

    }
}