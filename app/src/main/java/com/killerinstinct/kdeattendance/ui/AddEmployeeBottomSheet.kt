package com.killerinstinct.kdeattendance.ui

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.killerinstinct.kdeattendance.R
import com.killerinstinct.kdeattendance.Utils
import com.killerinstinct.kdeattendance.localdb.KDEdatabase
import com.killerinstinct.kdeattendance.models.Employee
import com.killerinstinct.kdeattendance.repository.MainRepository
import com.killerinstinct.kdeattendance.viewmodels.MainViewModel
import com.killerinstinct.kdeattendance.viewmodels.MainViewModelProviderFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEmployeeBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME,R.style.DialogStyle)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainRepository = MainRepository(KDEdatabase(requireActivity(),Utils.ALL_EMPLOYEES))
        val viewModelProviderFactory = MainViewModelProviderFactory(mainRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        view.findViewById<Button>(R.id.btn_add)?.setOnClickListener {
            val employeelist = view.findViewById<EditText>(R.id.et_addemployee)?.text?.split("\n")
            if (employeelist != null) {
                for (i in employeelist.indices){
                    val employee = Employee(employeelist[i],Utils.spinnerCategory[0])
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.addEmployee(employee)
                    }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_emplyee_bottom_sheet, container, false)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)


    }
}