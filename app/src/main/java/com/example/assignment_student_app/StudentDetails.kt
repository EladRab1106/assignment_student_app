package com.example.assignment_student_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_student_app.model.Student

class StudentDetails : AppCompatActivity() {
    private var nameTextView:TextView? = null
    private var idTextView:TextView? = null
    private var phoneTextView:TextView? = null
    private var addressTextView:TextView? = null
    private var checkBox:CheckBox? = null
    private var checkBoxText:TextView? = null
    private var editButton:Button? = null
    private var toolBar:androidx.appcompat.widget.Toolbar? = null

        companion object {
            fun newIntent(context: Context, student: Student): Intent {
                return Intent(context, StudentDetails::class.java).apply {
                    putExtra("student", student)
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        @Suppress("DEPRECATION")
        val student: Student? = intent.getParcelableExtra("student")
        if (student == null) {
            finish() // Exit if no student data is passed
            return
        }
        toolBar = findViewById(R.id.activity_student_details_tool_bar)
        toolBar?.setOnClickListener{
            finish()
        }
        editButton = findViewById(R.id.activity_student_details_edit_button)
        editButton?.setOnClickListener{
            val intent= EditStudent.newIntent(this,student)
            startActivity(intent)
        }


        bind(student)

    }

    fun bind(student: Student) {
        nameTextView = findViewById(R.id.activity_student_details_student_name_text_view)
        idTextView = findViewById(R.id.activity_student_details_student_id_text_view)
        phoneTextView = findViewById(R.id.activity_student_details_student_phone_text_view)
        addressTextView = findViewById(R.id.activity_student_details_student_address_text_view)
        checkBox = findViewById(R.id.activity_student_details_check_box)
        checkBoxText = findViewById(R.id.activity_student_details_student_is_check_text_view)
        nameTextView?.text = student.name
        idTextView?.text = student.id
        phoneTextView?.text = student.phone
        addressTextView?.text = student.address
        checkBox?.isChecked = student.isChecked
        checkBoxText?.text = if (student.isChecked) "Checked" else "Not Checked"

    }
}
