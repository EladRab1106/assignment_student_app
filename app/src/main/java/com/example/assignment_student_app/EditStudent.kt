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
import com.example.assignment_student_app.model.Model
import com.example.assignment_student_app.model.Student

class EditStudent : AppCompatActivity() {
    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var phoneTextView: TextView? = null
    private var addressTextView: TextView? = null
    private var checkBox: CheckBox? = null
    private var checkBoxText: TextView? = null
    private var cancelButton: Button? = null
    private var saveButton: Button? = null
    private var deleteButton: Button? = null
    private var oldId: String? = null
    companion object {
        fun newIntent(context: Context, student: Student): Intent {
            return Intent(context, EditStudent::class.java).apply {
                putExtra("student", student)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
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
        oldId = student.id
        cancelButton = findViewById(R.id.activity_edit_student_cancel_button)
        saveButton = findViewById(R.id.activity_edit_student_save_button)
        deleteButton = findViewById(R.id.activity_edit_student_delete_button)
        cancelButton?.setOnClickListener{
            finish()
        }
        saveButton?.setOnClickListener {
            // Update the student object with the new data
            student.name = nameTextView?.text.toString()
            student.id = idTextView?.text.toString()
            student.phone = phoneTextView?.text.toString()
            student.address = addressTextView?.text.toString()
            student.isChecked = checkBox?.isChecked ?: false

            // Find the index of the student in Model.shared.students
            val index = Model.shared.students.indexOfFirst { it.id == oldId }
            if (index != -1) {
                // Update the student in the list
                Model.shared.students[index] = student
            }

            // Pass the updated student back to the previous activity
            val resultIntent = Intent().apply { putExtra("new_student", student) }
            setResult(RESULT_OK, resultIntent)
            finish() // Close the activity
        }

        deleteButton?.setOnClickListener{
            Model.shared.students.remove(student)
            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)
            finish()
        }
        bind(student)
    }
    fun bind(student: Student) {
        nameTextView = findViewById(R.id.activity_edit_student_name_plain)
        idTextView = findViewById(R.id.activity_edit_student_id_plain)
        phoneTextView = findViewById(R.id.activity_edit_student_phone_plain)
        addressTextView = findViewById(R.id.activity_edit_student_address_plain)
        checkBox = findViewById(R.id.activity_edit_student_check_box)
        checkBoxText = findViewById(R.id.activity_edit_student_is_check_text_view)
        nameTextView?.text = student.name
        idTextView?.text = student.id
        phoneTextView?.text = student.phone
        addressTextView?.text = student.address
        checkBox?.isChecked = student.isChecked
        checkBoxText?.text = if (student.isChecked) "Checked" else "Not Checked"
    }

}