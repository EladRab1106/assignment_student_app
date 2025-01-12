package com.example.assignment_student_app.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Student(
    var name: String,
    var id:String,
    val AvatarUrl:String,
    var isChecked:Boolean,
    var phone:String,
    var address:String

) : Parcelable

