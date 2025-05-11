package com.pustovit.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pustovit.customlayout.ui.theme.CustomLayoutTheme
import com.pustovit.customlayout.ui.theme.MyColumn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyColumn(
                        gap = 16.dp,
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.LightGray)
                    ) {
                        ColoredBox(size = 64.dp, color = Color.Blue)
                        ColoredBox(size = 128.dp, color = Color.Blue)
                        ColoredBox(size = 256.dp, color = Color.Blue)
                    }
                }
            }
        }
    }
}

@Composable
private fun ColoredBox(size: Dp, color: Color) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color)
    )
}
