package com.meekoo.todonow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meekoo.todonow.R

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Normally collect flow and send events from UI
    }
}