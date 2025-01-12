package com.example.assignment_student_app.adapter

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_student_app.R
import com.example.assignment_student_app.R.*
import com.example.assignment_student_app.R.id.*
import com.example.assignment_student_app.model.Student
import com.example.assignment_student_app.onItemClickListener

class StudentViewHolder(
    itemView: View,
    listener: onItemClickListener?
): RecyclerView.ViewHolder(itemView){
    private var nameTextView: TextView?=null
    private var IdTextView: TextView?=null
    private var studentCheckBox: CheckBox?=null
    private var student: Student?=null

    init {
        nameTextView  =itemView.findViewById(student_row_name_text_view)
        IdTextView  =itemView.findViewById(student_row_id_text_view)
        this.studentCheckBox =itemView.findViewById(student_row_check_box)

        studentCheckBox?.apply {
            setOnClickListener {
                (tag as? Int)?.let { tag ->
                    student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                }
            }
        }

        itemView.setOnClickListener{
            Log.d("TAG,","Item Clicked on position $adapterPosition")
//                listener?.onItemClick(adapterPosition)
            listener?.onItemClick(student)
        }


    }
    fun bind(student: Student?, position: Int) {
        this.student=student
        nameTextView?.text=student?.name
        IdTextView?.text=student?.id

        studentCheckBox?.apply {
            isChecked=student?.isChecked?:false
            tag = position
        }
    }

}