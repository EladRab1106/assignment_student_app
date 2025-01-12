package com.example.assignment_student_app.model

class Model private constructor(){

    val students:MutableList<Student> = ArrayList()
    companion object{
        val shared = Model()
    }

    init {
        for (i in 0..20){
            val student=Student(
                name = "Ben Shapiro $i",
                id = i.toString(),
                AvatarUrl = "",
                isChecked = false
            )
            students.add(student)
        }
    }

}