package com.example.amadermess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.amadermess.databse.MessDataBase
import com.example.amadermess.model.MessMember
import com.example.amadermess.ui.theme.AmaderMessTheme
import com.example.amadermess.ui.theme.Primary
import com.example.amadermess.view.navigation.SetupNavigation
import com.example.amadermess.view.viewmodel.MainVMF
import com.example.amadermess.view.viewmodel.MainViewModel



/*
      https://stevdza-san.com/courses/to-do-app-with-jetpack-compose-mvvm-android-development/lectures/37513374
*/

class MainActivity : ComponentActivity() {


    private val viewModel by lazy {
        ViewModelProvider(
            this,
            MainVMF(MessDataBase.getInstance(this))
        )[MainViewModel::class.java]
    }


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = " Amader Mess",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(100.dp)
                            )
                        }, colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Primary,
                            titleContentColor = Color.White,
                        ),
                        navigationIcon = {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        onBackPressed()
                                    }
                            )
                        }
                    )
                }
            ) {
                Box(modifier = Modifier.padding(it)) {
                    SetupNavigation(viewModel)
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AmaderMessTheme {
        Greeting("Nahid")
    }
}