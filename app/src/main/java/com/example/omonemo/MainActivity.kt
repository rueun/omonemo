package com.example.omonemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var authStateListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //로그인 세션을 체크하는 부분
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->

            Log.e("LLPP", "로그인")

            var user = firebaseAuth.currentUser
            if(user != null){
                var iT = Intent(this, HomeActivity::class.java)
                startActivity(iT)
            }

        }

        //버튼입력을 통합처리
        btnClick()
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().addAuthStateListener (authStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener (authStateListener!!)
    }




    fun loginId(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(et_id.text.toString(), et_pw.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
    }


    fun btnClick(){
        //회원가입 버튼을 눌렀을때
        joinBtn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        //로그인 버튼을 눌렀을때
        loginBtn.setOnClickListener{
            loginId()
        }
    }

}