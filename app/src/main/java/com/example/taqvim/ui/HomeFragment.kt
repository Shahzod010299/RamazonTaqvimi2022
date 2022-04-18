package com.example.taqvim.ui

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.res.AssetManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.taqvim.R
import com.example.taqvim.databinding.FragmentHomeBinding
import com.example.taqvim.utils.ValueStatic
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentHomeBinding
    lateinit var  saxarlikMediaPlayer: MediaPlayer;
    lateinit var iftorlikMediaPlayer: MediaPlayer;

    var dateList: ArrayList<String> = ArrayList()
    var timeList: ArrayList<String> = ArrayList()
    var time2List: ArrayList<String> = ArrayList()

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var calendar: Calendar

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val sdf = SimpleDateFormat("dd")
        val currentDate = sdf.format(Date())

        val cal = Calendar.getInstance()
        val month_date = SimpleDateFormat("MMMM")
        val month_name = month_date.format(cal.time)

        binding.textSana.text = month_name.toString().plus("  "+currentDate+" / "+ currentDate + " - kun")

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("calendar")

            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                dateList.add(userDetail.getString("date"))
                timeList.add(userDetail.getString("time"))
                time2List.add(userDetail.getString("time2"))
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        for (i in 0 until dateList.size){
            val new_date = SimpleDateFormat("dd.MM.yyyy").format(Date()).toString()
            val new_time = SimpleDateFormat("HH:mm").format(Date()).toString()

            if (dateList[i]==new_date){
                binding.tvIftrolikTime.text = time2List[i]
                binding.tvSaxarlikTime.text = timeList[i]
            }

        }


        binding.cardSaharlik.setOnClickListener {

            volumeFunStopIftorlik()

            if (!ValueStatic.bool_saharlik){

                saxarlikMediaPlayer = MediaPlayer.create(requireActivity(),R.raw.saharlik);
                binding.imgSaharlikSound.setImageResource(R.drawable.ic_pause)
                try {
                    saxarlikMediaPlayer.start()
                } catch (e: Exception){
                }
                ValueStatic.bool_saharlik = true

            }
            else {

                binding.imgSaharlikSound.setImageResource(R.drawable.ic_volume)
                try {
                    saxarlikMediaPlayer.stop()
                } catch (e: Exception){
                }
                ValueStatic.bool_saharlik = false
            }


        }

        binding.cardIftorlik.setOnClickListener {
             volumeFunStopSaharlik()

            if (!ValueStatic.bool_iftorlik){

                iftorlikMediaPlayer = MediaPlayer.create(requireActivity(),R.raw.iftorlik);
                binding.imgIftorlikSound.setImageResource(R.drawable.ic_pause)
                try {
                    iftorlikMediaPlayer.start()
                } catch (e: Exception){
                }
                ValueStatic.bool_iftorlik = true

            }
            else {

                binding.imgIftorlikSound.setImageResource(R.drawable.ic_volume)
                try {
                    iftorlikMediaPlayer.stop()
                } catch (e: Exception){
                }
                ValueStatic.bool_iftorlik = false
            }
        }

        binding.switchEslatma.setOnClickListener {
            val dialogFragment = DialogTimerFragment()
            val fragmentManager: FragmentManager? =
                activity?.getSupportFragmentManager()
            val fragmentTransaction = fragmentManager?.beginTransaction();
            fragmentTransaction?.replace(R.id.fragmentContainerView,dialogFragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()

        }

        return binding.root
    }



    private fun creatNotificationCannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "dk channels"
            val desription = "dk diskription"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("dasturchilarKlubi",name,importance)
            channel.description = desription

            val notifyManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )

            notifyManager.createNotificationChannel(channel)
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
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onPause() {
        volumeFunStopIftorlik();
        volumeFunStopSaharlik();
        super.onPause()
    }

    override fun onResume() {
        volumeFunStopIftorlik();
        volumeFunStopSaharlik();
        super.onResume()
    }

    private fun volumeFunStopIftorlik() {
            ValueStatic.bool_iftorlik = false

            try {
                binding.imgIftorlikSound.setImageResource(R.drawable.ic_volume)
                iftorlikMediaPlayer.stop()
            } catch (e: Exception){
            }

    }

    private fun volumeFunStopSaharlik() {
         ValueStatic.bool_saharlik = false

         try {
             binding.imgSaharlikSound.setImageResource(R.drawable.ic_volume)
             saxarlikMediaPlayer.stop()
         } catch (e: Exception){
         }
    }

    override fun onDestroy() {
        volumeFunStopIftorlik();
        volumeFunStopSaharlik();
        super.onDestroy()
    }

}