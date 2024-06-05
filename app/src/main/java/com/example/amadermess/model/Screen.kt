package com.example.amadermess.model

sealed class Screen(val route : String) {

    object MemberScreen : Screen(route = "members")
    object AddMember : Screen(route = "add_members")
}