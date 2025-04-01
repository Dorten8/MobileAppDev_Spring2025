package dk.itu.garbagev6_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class DeleteItemFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(R.layout.fragment_delete_item, container, false)

        val viewModel = ViewModelProvider(requireActivity())[SharedItemsVM::class.java]

        val deleteItemButton: Button = v.findViewById(R.id.delete_item_bt)

        val deleteItemEditText: EditText = v.findViewById(R.id.delete_item_et)

        deleteItemButton.setOnClickListener {
            val msg = viewModel.deleteItem(deleteItemEditText.text.toString().trim(), requireContext())
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            deleteItemEditText.text.clear()
        }

        return v
    }
}