package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var remaingTime:Long = 25*60*1000
    var timerCounter:CountDownTimer?= null

    var isTimerRuning  = false
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.startBtn.setOnClickListener {

        if(!isTimerRuning){
            isTimerRuning = true
            timerCounter = object : CountDownTimer(25*1000*60,1 * 1000){
                override fun onTick(millisUntilFinished: Long) {
                    remaingTime = millisUntilFinished
                    binding.progressBar.progress = remaingTime.toDouble().div(25*1000*60).times(100).toInt()
                    updateTime()

                }

                override fun onFinish() {
                    isTimerRuning = false
                    binding.progressBar.progress= 100

                    Toast.makeText(binding.root.context,"Time Finish" ,Toast.LENGTH_SHORT).show()
                }
            }.start()
            binding.titleTv.text = "Keep Going..."
        }
        }
        binding.resetTv.setOnClickListener {
            binding.titleTv.text = "Start Timer"
            remaingTime = 25*1000*60
            updateTime()
            isTimerRuning = false
            timerCounter?.cancel()
            binding.progressBar.progress = 100
        }









    }
    fun updateTime(){
        val min = remaingTime.div(1000).div(60)
        val sec = remaingTime.div(1000) % 60
        val formatedTime = String.format("%02d:%02d",min,sec)
        binding.timeTv.text = formatedTime
    }
}