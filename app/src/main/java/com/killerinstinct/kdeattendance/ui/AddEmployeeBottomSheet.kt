package com.killerinstinct.kdeattendance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.killerinstinct.kdeattendance.R
import com.killerinstinct.kdeattendance.Utils
import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.models.Employee
import com.killerinstinct.kdeattendance.repository.MainRepository
import com.killerinstinct.kdeattendance.viewmodels.MainViewModel
import com.killerinstinct.kdeattendance.viewmodels.MainVMProviderFactory

class AddEmployeeBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //spinner code
        val spinner = view.findViewById<Spinner>(R.id.category_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                dialog!!.context,
                android.R.layout.simple_spinner_item,
                Utils.spinnerCategory
            )
            spinner.adapter = adapter
        }


        val mainRepository = MainRepository(KDEdatabase(requireActivity(), Utils.ALL_EMPLOYEES))
        val viewModelProviderFactory = MainVMProviderFactory(mainRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(MainViewModel::class.java)

        val editText = view.findViewById<EditText>(R.id.et_addemployee)
        val addbtn = view.findViewById<Button>(R.id.btn_add)

        addbtn.setOnClickListener {
            val employeelist = editText.text?.split("\n")
            if (employeelist != null && employeelist.isNotEmpty()) {
                for (i in employeelist.indices) {
                    val employee = Employee(employeelist[i], Utils.spinnerCategory[0])
                    viewModel.addEmployee(employee)
                    Toast.makeText(requireContext(), "Employee Added", Toast.LENGTH_SHORT).show()
                    editText.text.clear()
                    viewModel.getAllEmployees()
                }
            } else {
                Toast.makeText(requireContext(), "Enter valid name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_emplyee_bottom_sheet, container, false)
    }

//    override fun onCancel(dialog: DialogInterface) {
//        super.onCancel(dialog)
//        (activity as MainActivity).setupRecyclerView(viewModel.employeeListLiveData.value)
//    }
}