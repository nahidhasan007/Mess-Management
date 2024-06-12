package com.example.amadermess.view.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.amadermess.R
import com.example.amadermess.base.CustomLabelText
import com.example.amadermess.model.MessMember
import com.example.amadermess.view.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMessMember(navController: NavController?, viewModel: MainViewModel? = null) {

    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val deposit = remember { mutableStateOf(0.0) }
    val expense = remember { mutableStateOf(0.0) }
    val meals = remember { mutableStateOf(0.0) }
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

        val mModifier: Modifier =Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
        val mValue: String = deposit.value.toString()
        val mColors: TextFieldColors =  TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black
        )

        val mShape: Shape = RoundedCornerShape(8.dp)
        val kmKeyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number);
        val monValueChange: (String) -> Unit = { input -> deposit.value = input.toDouble() }
        OutlinedTextField(
            modifier = mModifier,
            value = mValue,  // this is the culprit ->> deposit.value,
            label = { CustomLabelText(stringResource(id = R.string.deposit)) },
            colors = mColors,
            shape = mShape,
            keyboardOptions = kmKeyboardOptions,
            onValueChange = monValueChange
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = expense.value.toString(),
            label = { CustomLabelText(stringResource(id = R.string.expense)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { value : String ->
                expense.value = value.toDouble()
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = meals.value.toString(),
            label = { CustomLabelText(stringResource(id = R.string.meals)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { value: String ->
                meals.value = value.toDouble()
            },
        )

        val member = MessMember(
            name = name.value,
            phone = phone.value,
            deposit = deposit.value,
            currentExpense = expense.value,
            totalMeal = meals.value
        )

        Button(
            onClick = {
                Log.e("Data fetched or not", member.toString())
                viewModel?.insertIntoMessDb(member)
                navController?.navigateUp()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(
                text = stringResource(id = R.string.add_member),
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    AddMessMember(navController = null, viewModel = null)
}