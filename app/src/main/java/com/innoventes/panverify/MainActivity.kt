package com.innoventes.panverify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.innoventes.panverify.ui.PanScreen
import com.innoventes.panverify.viewmodel.PanViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: PanViewModel by viewModels()
            PanScreen(
                viewModel = viewModel
            )
        }
    }
}
