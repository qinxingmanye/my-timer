package com.example.timer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var seconds = 0
    private var isRunning = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnReset = findViewById<Button>(R.id.btnReset)

        val runnable = object : Runnable {
            override fun run() {
                if (isRunning) {
                    seconds++
                    tvTimer.text = String.format("%02d:%02d", seconds / 60, seconds % 60)
                    handler.postDelayed(this, 1000)
                }
            }
        }

        btnStart.setOnClickListener {
            isRunning = !isRunning
            btnStart.text = if (isRunning) "暂停" else "开始"
            if (isRunning) handler.post(runnable)
        }

        btnReset.setOnClickListener {
            isRunning = false
            seconds = 0
            tvTimer.text = "00:00"
            btnStart.text = "开始"
        }
    }
}
