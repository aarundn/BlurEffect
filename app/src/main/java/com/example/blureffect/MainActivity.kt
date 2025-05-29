package com.example.blureffect

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blureffect.ui.theme.BlurEffectTheme

// Constants for different blur levels
const val LOW = 0.01f
const val MEDIUM = 25f
const val HIGH = 50f

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Using a mutable state to control the blur value
            var blurValue by remember { mutableFloatStateOf(LOW) }
            BlurEffectTheme {
                // Using RenderEffect to apply a blur effect
                // Note: RenderEffect is only available on Android S (API 31) and above
                val blurEffect = remember(blurValue) {
                        RenderEffect.createBlurEffect(blurValue, blurValue, Shader.TileMode.CLAMP).asComposeRenderEffect()
                }
                Scaffold(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .clickable {
                                // Change the blur value on click
                                // This can be adjusted to LOW, MEDIUM, or HIGH based on your needs

                                blurValue = when (blurValue) {
                                    LOW -> {
                                        Toast.makeText(
                                            this,
                                            "from LOW to MEDIUM Blur",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        MEDIUM
                                    }

                                    MEDIUM -> {
                                        Toast.makeText(
                                            this,
                                            "from MEDIUM to HIGH Blur",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        HIGH
                                    }

                                    else -> {
                                        Toast.makeText(
                                            this,
                                            "from HIGH to LOW Blur",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        LOW
                                    }
                                }
                            }
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.large
                            )
                            // Applying the blur effect to the Scaffold
                            .graphicsLayer {
                                renderEffect = blurEffect
                            },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Greeting(
                            "Android", modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.background,
            modifier = modifier.background(color = MaterialTheme.colorScheme.primary)
        )
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun GreetingPreview() {
        BlurEffectTheme {
            Greeting("Android")
        }
    }
}