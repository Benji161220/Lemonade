package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

//Lemonade
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }

    @Preview
    @Composable
    fun LemonApp() {
        var currentStep by rememberSaveable { mutableStateOf(1) }
        var squeezesLeft by remember { mutableStateOf(0) }
        val imageSize by animateDpAsState(
            targetValue = if (currentStep == 2) 250.dp else 200.dp,
            label = "imageSize"
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            AnimatedVisibility(
                visible = currentStep == 1,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                ) {
                    Text(text = stringResource(R.string.Lemon))
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.Lemon),
                        modifier = Modifier
                            .size(imageSize)
                            .clickable {
                                squeezesLeft = Random.nextInt(2, 7)
                                currentStep = 2
                            }
                    )
                }
            }


            AnimatedVisibility(
                visible = currentStep == 2,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                ) {
                    Text(text = stringResource(R.string.keep_tapping))
                    Spacer(
                        modifier = Modifier.height(
                            32
                                .dp
                        )
                    )
                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.keep_tapping),
                        modifier = Modifier
                            .size(imageSize)
                            .clickable {
                                squeezesLeft--
                                if (squeezesLeft <= 0) {
                                    currentStep = 3
                                }
                            }
                    )
                }
            }


            AnimatedVisibility(
                visible = currentStep == 3,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                ) {
                    Text(text = stringResource(R.string.Glass_of_lemonade))
                    Spacer(
                        modifier = Modifier.height(
                            32
                                .dp
                        )
                    )
                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.Glass_of_lemonade),
                        modifier = Modifier
                            .size(imageSize)
                            .clickable {
                                currentStep = 4
                            }
                    )
                }
            }


            AnimatedVisibility(
                visible = currentStep == 4,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                ) {
                    Text(text = stringResource(R.string.Empty_glass))
                    Spacer(
                        modifier = Modifier.height(
                            32
                                .dp
                        )
                    )
                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.Empty_glass),
                        modifier = Modifier
                            .size(imageSize)
                            .clickable {
                                currentStep = 1
                            }
                    )
                }
            }
        }
    }
}

