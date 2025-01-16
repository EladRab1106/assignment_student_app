package com.example.assignment_student_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
    companion object {
        const val REQUEST_CODE_NEW_STUDENT = 1001
        const val REQUEST_CODE_EDIT_STUDENT = 1002
    }
    var students : MutableList<Student>?=null
    var plusButton: Button?=null

    override
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_NEW_STUDENT && resultCode == RESULT_OK) {
            val student: Student? = data?.getParcelableExtra("new_student")
            student?.let {
                students?.add(it)
                val adapter = findViewById<RecyclerView>(R.id.students_list_activity_recycler_view).adapter as StudentsRecyclerAdapter
                adapter.notifyDataSetChanged()
            }
        }
    }
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
        plusButton=findViewById(R.id.activity_students_recycler_view_plus_button)
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
                student?.let {
                    val intent=StudentDetails.newIntent(this@StudentsRecyclerViewActivity,student)
                    startActivity(intent)
                }
            }
        }
        plusButton?.setOnClickListener{
            // navigate to new student activity
            val intent : Intent = Intent(this,NewStudent::class.java)
            startActivityForResult(intent, REQUEST_CODE_NEW_STUDENT)
        }

        recyclerView.adapter=adapter
    }
}