package com.example.omonemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_main.*

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        btnClick()
    }
    fun createEmailId(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(id_tx.text.toString(), pw_tx.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
    }

    fun btnClick(){
        //회원가입 버튼을 눌렀을때
        join_btn.setOnClickListener{
            createEmailId()
        }

        cancel_btn.setOnClickListener {

            // MainActivity에서 넘어온 Activity를 종료하고 싶으면
            finish()
        }
    }
}