package com.example.amadermess.base

import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FilledButton(btnText : String = "", onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(btnText)
    }
}