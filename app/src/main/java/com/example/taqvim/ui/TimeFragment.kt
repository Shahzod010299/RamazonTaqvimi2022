package com.example.taqvim.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taqvim.R
import com.example.taqvim.adapter.AdapterDialogFragmernt
import com.example.taqvim.databinding.FragmentDialogTimerBinding
import com.example.taqvim.models.DataDialog
import kotlinx.android.synthetic.main.fragment_dialog_timer.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DialogTimerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val listData = ArrayList<DataDialog>()
    private lateinit var adapter : AdapterDialogFragmernt
    lateinit var binding: FragmentDialogTimerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDialogTimerBinding.inflate(layoutInflater)

        loadRV()
        adapter = AdapterDialogFragmernt(listData)
        binding.rvDialog.adapter = adapter

        binding.btnClose.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        return binding.root
    }

    private fun loadRV() {

        listData.add(DataDialog( "Toshkent shahri","0 daqiqa"    ))
        listData.add(DataDialog( "Andijon viloyati","-11 daqiqa"    ))
        listData.add(DataDialog( "Buxoro viloyati","22 daqiqa"    ))
        listData.add(DataDialog( "Jizzax viloyati","8 daqiqa"    ))
        listData.add(DataDialog( "Qashqadaryo viloyati","9 daqiqa"    ))
        listData.add(DataDialog( "Navoiy viloyati","13 daqiqa"    ))
        listData.add(DataDialog( "Namangan viloyati","-14 daqiqa"    ))
        listData.add(DataDialog( "Samarqand viloyati","8 daqiqa"    ))
        listData.add(DataDialog( "Surxondaryo viloyati","15 daqiqa"    ))
        listData.add(DataDialog( "Sirdaryo viloyati","4 daqiqa"    ))
        listData.add(DataDialog( "Toshkent viloyati","0 daqiqa"    ))
        listData.add(DataDialog( "Farg‘ona viloyati","-12 daqiqa"    ))
        listData.add(DataDialog( "Xorazm viloyati","33 daqiqa"    ))
        listData.add(DataDialog( "Qoraqalpog‘iston \n Respublikasi","37 daqiqa"    ))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogTimerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogTimerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}