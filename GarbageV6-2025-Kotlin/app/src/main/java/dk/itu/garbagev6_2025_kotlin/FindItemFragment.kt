package dk.itu.garbagev6_2025_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class FindItemFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(R.layout.fragment_find_item, container, false)

        val viewModel = ViewModelProvider(requireActivity())[FindItemVM::class.java]

        val findItemButton: Button = v.findViewById(R.id.find_item_bt)
        val addItemButton: Button = v.findViewById(R.id.find_add_item_bt)
        val deleteItemButton: Button = v.findViewById(R.id.find_delete_item_bt)

        val findItemEditText: EditText = v.findViewById<EditText?>(R.id.find_item_et)

        findItemButton.setOnClickListener {
            activity?.let { fragmentActivity ->
                viewModel.onFindItemButtonClick(
                    findItemEditText,
                    fragmentActivity
                )
            }
        }

        findItemEditText.setOnClickListener{
            viewModel.onFindItemEditTextClick(findItemEditText)
        }

        addItemButton.setOnClickListener {
            viewModel.onAddItemButtonClicked()
        }

        deleteItemButton.setOnClickListener {
            viewModel.onDeleteItemButtonClicked()
        }

        // Observe LiveData for navigation
        viewModel.navigateToAddItem.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                val intent = Intent(requireContext(), AddItemActivity::class.java)
                startActivity(intent)
                viewModel.onNavigationHandled()  // Reset event after navigation
            }
        }

        viewModel.navigateToDeleteItem.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                val intent = Intent(requireContext(), DeleteItemActivity::class.java)
                startActivity(intent)
                viewModel.onNavigationHandled()  // Reset event after navigation
            }
        }

        return v
    }
}