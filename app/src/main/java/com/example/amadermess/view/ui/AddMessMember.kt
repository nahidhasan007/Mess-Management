package com.example.amadermess.view.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.amadermess.R
import com.example.amadermess.base.CustomLabelText
import com.example.amadermess.base.CustomTextField
import com.example.amadermess.model.MessMember
import com.example.amadermess.view.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMessMember(navController: NavController, viewModel: MainViewModel? = null) {

    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val deposit = remember { mutableStateOf("") }
    val expense = remember { mutableStateOf("") }
    val meals = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = name.value,
            label = { CustomLabelText(stringResource(id = R.string.name)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardActions = KeyboardActions.Default,
            onValueChange = { value ->
                name.value = value
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = phone.value,
            label = { CustomLabelText(stringResource(id = R.string.phone)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardActions = KeyboardActions.Default,
            onValueChange = { value ->
                phone.value = value
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = deposit.value,
            label = { CustomLabelText(stringResource(id = R.string.deposit)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardActions = KeyboardActions.Default,
            onValueChange = { value ->
                deposit.value = value
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = expense.value,
            label = { CustomLabelText(stringResource(id = R.string.expense)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardActions = KeyboardActions.Default,
            onValueChange = { value ->
                expense.value = value
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = meals.value,
            label = { CustomLabelText(stringResource(id = R.string.meals)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardActions = KeyboardActions.Default,
            onValueChange = { value ->
                meals.value = value
            },
        )

        val member = MessMember(
            name = name.value,
            phone = phone.value,
            deposit = deposit.value,
            currentExpense = expense.value,
            totalMeal = meals.value
        )

        Text(
            text = "Add Member",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Log.e("Data fetched or not", member.toString())
                    viewModel?.insertIntoMessDb(member)
                }
        )
    }
}