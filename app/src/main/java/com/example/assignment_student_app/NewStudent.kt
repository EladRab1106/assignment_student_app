package com.example.assignment_student_app

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_student_app.model.Model
import com.example.assignment_student_app.model.Student

class NewStudent : AppCompatActivity() {
    var students: MutableList<Student>? = null
    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var phoneTextView: TextView? = null
    private var addressTextView: TextView? = null
    private var checkBox: CheckBox? = null
    private var checkBoxText: TextView? = null
    private var cancelButton: Button? = null
    private var saveButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cancelButton = findViewById(R.id.activity_new_student_cancel_button)
        saveButton = findViewById(R.id.activity_new_student_save_button)
        cancelButton?.setOnClickListener {
            finish()
        }
        students= Model.shared.students
        nameTextView = findViewById(R.id.activity_new_student_name_plain)
        idTextView = findViewById(R.id.activity_new_student_id_plain)
        phoneTextView = findViewById(R.id.activity_new_student_phone_plain)
        addressTextView = findViewById(R.id.activity_new_student_address_plain)
        checkBox = findViewById(R.id.activity_new_student_check_box)
        checkBoxText = findViewById(R.id.activity_new_student_student_is_check_text_view)
        saveButton?.setOnClickListener {
            val student:Student = Student(
                name = nameTextView?.text.toString(),
                id = idTextView?.text.toString(),
                phone = phoneTextView?.text.toString(),
                address = addressTextView?.text.toString(),
                isChecked = checkBox?.isChecked ?: false,
                AvatarUrl = "man.png"

            )
            val resultIntent = intent.apply { putExtra("new_student", student) }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}