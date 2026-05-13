package com.nusantara.culture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nusantara.culture.navigation.NusantaraNavGraph
import com.nusantara.culture.ui.theme.NusantaraCultureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NusantaraCultureTheme {
                NusantaraNavGraph()
            }
        }
    }
}
