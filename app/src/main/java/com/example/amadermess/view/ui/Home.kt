package com.example.amadermess.view.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.amadermess.R
import com.example.amadermess.model.MessMember
import com.example.amadermess.model.Screen
import com.example.amadermess.ui.theme.LightGray
import com.example.amadermess.view.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MembersScreen(navController: NavController? = null) {

}


@Composable
fun ShowMessMembers(navController: NavController? = null, viewModel: MainViewModel? = null) {

    LaunchedEffect("") {
        viewModel?.setTotalValues()
        viewModel?.getMessMembers()

    }
    Log.e("TotalMeals", "${viewModel?.totalMeals?.collectAsState() }}")
    Log.e("TotalExpense", "${viewModel?.totalExpense?.collectAsState()}")
    Log.e("TotalDeposit", "${viewModel?.totalDeposit?.collectAsState()}")

    val messMembers = viewModel?.messMemberList?.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            Text(
                color = Color.Black,
                text = "Add Mess Member",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable {
                        navController?.navigate(Screen.AddMember.route)
                    }
            )
        }
        if (messMembers?.value?.isNotEmpty()==true)

            items(messMembers.value) { messMember ->
                ItemMessMember(member = messMember, viewModel)
            }
    }
}


@Composable
fun ItemMessMember(member: MessMember? = null, viewModel: MainViewModel?) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = LightGray, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {

        Text(
            text = "Name: ${member?.name ?: ""}",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )


        Text(
            text = "Phone: ${member?.phone ?: ""}",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Deposit: ${member?.deposit ?: ""}",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Current Expense: ${member?.currentExpense ?: ""}",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Total Meal: ${member?.totalMeal ?: ""}",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Button(
            onClick = {
                Log.e("Clicked delete btn", "Deleting")
                member?.let {
                    viewModel?.deleteMembers(member)
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text(
                text = stringResource(id = R.string.delete),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = {
                Log.e("Clicked delete btn", "Deleting")
                member?.let {
                    viewModel?.deleteMembers(member)
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text(
                text = stringResource(id = R.string.delete),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ShowItemMessMember() {
    ItemMessMember(member = null, null)
}