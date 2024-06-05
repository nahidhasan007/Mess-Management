package com.example.amadermess.view.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.amadermess.R
import com.example.amadermess.base.CustomTextField
import com.example.amadermess.view.viewmodel.MainViewModel

@Composable
fun AddMessMember(navController: NavController, viewModel: MainViewModel? = null) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
    ) {
        CustomTextField(
            labelValue = stringResource(R.string.name),
            viewModel = viewModel
        )
        CustomTextField(
            labelValue = stringResource(id = R.string.phone),
            viewModel = viewModel
        )
        CustomTextField(
            labelValue = stringResource(R.string.deposit),
            viewModel = viewModel
        )
        CustomTextField(
            labelValue = stringResource(id = R.string.expense),
            viewModel = viewModel
        )
        CustomTextField(
            labelValue = stringResource(id = R.string.meals),
            viewModel = viewModel
        )
        Text(
            text = "Add Member",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp).clickable {
                Log.e("Data fetched or not", viewModel?.member.toString())
                viewModel?.member?.let { viewModel.insertIntoMessDb(it) }
            }
        )
    }
}