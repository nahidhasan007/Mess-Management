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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.amadermess.model.MessMember
import com.example.amadermess.model.Screen
import com.example.amadermess.ui.theme.Gray
import com.example.amadermess.ui.theme.LightGray
import com.example.amadermess.view.viewmodel.MainViewModel

class MembersScreen(navController: NavController? = null) {

}


@Composable
fun ShowMessMembers(navController: NavController? = null, viewModel: MainViewModel? = null) {
    val messMembers = viewModel?._messMemberList
    Log.e("MessMembers", messMembers.toString())
    LazyColumn( modifier = Modifier.fillMaxSize(),
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
        if (messMembers != null)

            items(messMembers) { messMember ->
                ItemMessMember(member = messMember)
            }
    }
}


@Composable
fun ItemMessMember(member: MessMember) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = LightGray, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        member.name?.let {
            Text(
                text = "Name: $it",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
        member.phone?.let {
            Text(
                text = "Phone: $it",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Text(
            text = "Deposit: ${member.deposit ?: ""}",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Current Expense: ${member.currentExpense ?: ""}",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Total Meal: ${member.totalMeal ?: ""}",
            fontSize = 18.sp ,
            modifier = Modifier.padding(8.dp)
        )
    }
}