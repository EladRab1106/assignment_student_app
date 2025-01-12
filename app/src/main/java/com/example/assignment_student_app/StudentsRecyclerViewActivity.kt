package com.example.assignment_student_app

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_student_app.adapter.StudentsRecyclerAdapter
import com.example.assignment_student_app.model.Model
import com.example.assignment_student_app.model.Student

interface onItemClickListener{
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class StudentsRecyclerViewActivity : AppCompatActivity() {
    var students : MutableList<Student>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        students=Model.shared.students
        val recyclerView: RecyclerView =findViewById(R.id.students_list_activity_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        val adapter= StudentsRecyclerAdapter(students)
        adapter.listener=object :onItemClickListener{
            override fun onItemClick(position: Int) {
                Log.d("TAG,","on click activity on position $position")
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG,","on student click name: ${student?.name}")
            }
        }
        recyclerView.adapter=adapter
    }
}