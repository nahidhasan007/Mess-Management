package com.example.amadermess.base

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.amadermess.R
import com.example.amadermess.view.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    labelValue: String,
    leadingIcon: ImageVector? = null,
    viewModel: MainViewModel? = null
) {

    val textValue = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        label = { Text(text = labelValue) },
        value = textValue.value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black
        ),
        shape = RoundedCornerShape(8.dp),
        keyboardActions = KeyboardActions.Default,
        onValueChange = { value ->
            textValue.value = value
        },
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = "")
            }
        }

    )
    when (labelValue) {
        R.string.name.toString() -> {
            Log.e("I am inserting v1", labelValue)
            viewModel?.member?.name = textValue.toString()
        }

        R.string.phone.toString() -> {
            Log.e("I am inserting v2", labelValue)
            viewModel?.member?.phone = textValue.toString()
        }

        R.string.deposit.toString() -> {
            Log.e("I am inserting v3", labelValue)
            viewModel?.member?.deposit = textValue.toString()
        }

        R.string.expense.toString() -> {
            Log.e("I am inserting v4", labelValue)
            viewModel?.member?.currentExpense = textValue.toString()
        }

        else -> {
            Log.e("I am inserting v5", labelValue)
            viewModel?.member?.totalMeal = textValue.toString()
        }

    }

}