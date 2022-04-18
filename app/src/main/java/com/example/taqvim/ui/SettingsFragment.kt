package com.example.taqvim.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taqvim.databinding.FragmentSettingsBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
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

    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        // youtube
        binding.cardView.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/DasturchilarKlubi"))
            startActivity(myIntent)
        }
         // instagram
        binding.cardView2.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/dasturchilarklubi/?hl=en"))
            startActivity(myIntent)
        }
         // facebook
        binding.cardView3.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/dasturchilarklubi"))
            startActivity(myIntent)
        }
         // telegram
        binding.cardView4.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/dasturchilarklubi"))
            startActivity(myIntent)
        }

          // web
        binding.cardView5.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://d-klub.uz/"))
            startActivity(myIntent)
        }

        // location
        binding.cardView6.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:39.655819, 66.963647")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}