package com.sofiienko.game.composelabs2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofiienko.game.composelabs2.ui.theme.ComposeLabs2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLabs2Theme {
                AppView()
            }
        }
    }
}

@Composable
private fun AppView() {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(
            onContinueClick = {shouldShowOnboarding = false}
        )
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = listOf("Android", "Compose")) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(Modifier.padding(4.dp)) {
            names.forEach {
                Greeting(name = it)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isExpaned by remember { mutableStateOf(false) }
    val extraPadding = if (isExpaned) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1.toFloat())
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello ")
                Text(text = "$name!")
            }

            OutlinedButton(
                onClick = { isExpaned = !isExpaned },
            ) {
                Text(
                    text = if (isExpaned) "Show less" else "Show more",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClick: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the basics codelab")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = { onContinueClick() }
            ) {
                Text(text = "Continue")
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeLabs2Theme {
        AppView()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun PreviewOnboardingScreen() {
    ComposeLabs2Theme {
        OnboardingScreen(onContinueClick = {})
    }
}
