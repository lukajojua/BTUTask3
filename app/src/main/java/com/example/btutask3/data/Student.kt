package com.example.btutask3.data

data class Student(
    val firstName: String,
    val lastName: String,
    val personalNumber: String,
    val profileImage: String,
    val email: String
)
{
    constructor() : this("", "","","","")
}