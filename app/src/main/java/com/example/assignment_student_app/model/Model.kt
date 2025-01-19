package com.example.assignment_student_app.model

class Model private constructor(){

    val students:MutableList<Student> = ArrayList()
    companion object{
        val shared = Model()
    }

    init {
        for (i in 0..0){
            val student=Student(
                name = "elad rabinovitch $i",
                id = i.toString(),
                AvatarUrl = "",
                isChecked = false,
                phone = "1234567890 $i",
                address = "1234 Main St $i"
            )
            students.add(student)
        }
    }

}