package com.example.taqvim.ui

import android.content.res.AssetManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taqvim.R
import com.example.taqvim.adapter.AdapterCalendar
import com.example.taqvim.databinding.FragmentColendarBinding
import com.example.taqvim.models.DataCalendar
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ColendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ColendarFragment : Fragment() {
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

    lateinit var binding: FragmentColendarBinding
    val dataList = ArrayList<DataCalendar>()
    private lateinit var adapter: AdapterCalendar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentColendarBinding.inflate(layoutInflater)


        loadRv()
        adapter = AdapterCalendar(dataList)
        binding.rv.adapter = adapter



        return binding.root
    }

    private fun loadRv() {
        val listdate: ArrayList<String> = ArrayList()
        val listSaharlikTime: ArrayList<String> = ArrayList()
        val listIftorlikTime: ArrayList<String> = ArrayList()
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("calendar")

            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                listdate.add(userDetail.getString("date"))
                listSaharlikTime.add(userDetail.getString("time"))
                listIftorlikTime.add(userDetail.getString("time2"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        for (i in 0 until listdate.size) {
            dataList.add(
                DataCalendar(
                    listdate[i],
                    listSaharlikTime[i],
                    listIftorlikTime[i]
                )
            )
        }

    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        val assets: AssetManager = requireActivity().assets
        try {
            val inputStream = assets.open("tayyor.json");
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ColendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ColendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}