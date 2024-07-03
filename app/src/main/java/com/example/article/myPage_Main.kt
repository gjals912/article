package com.example.article

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class myPage_Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_page_main)

        fun showPopup(){
            val inflater = LayoutInflater.from(this)
            val popupView = inflater.inflate(R.layout.mypage_withdrawl_check, null)
            val textview :TextView = popupView.findViewById(R.id.withdrawl_popup)

            val alertDialog = AlertDialog.Builder(this)

                .setPositiveButton("탈퇴") {dialog, which ->
                    Toast.makeText(applicationContext, "탈퇴되었습니다.", Toast.LENGTH_SHORT)
                }
                .setNeutralButton("취소", null)
                .create()

            alertDialog.setView(popupView)
            alertDialog.show()
        }

        var myPageNickname = findViewById<EditText>(R.id.et_mypage_nickname)
        var myPageStartupField = findViewById<EditText>(R.id.et_myPageStartupField)
        var myPagePassword = findViewById<EditText>(R.id.et_myPagePassword)
        var myPagePasswordCheck = findViewById<EditText>(R.id.et_myPagePasswordCheck)

        myPageNickname.isEnabled = false
        myPageStartupField.isEnabled = false
        myPagePassword.isEnabled = false
        myPagePasswordCheck.isEnabled = false

        // user1 인스턴스 생성 가정 시 코드
//        var userNickname = user1.nickname
//        var userStartupField = user1.startupField
//        var userPassword = user1.password
//        var userPasswordCheck = user1.passwordCheck

        var userNickname = intent.getStringExtra("")
        var userStartupField = intent.getStringExtra("")
        var userPassword = intent.getStringExtra("")
        var userPasswordCheck = intent.getStringExtra("")

        val btn_myPageEdit = findViewById<Button>(R.id.btn_myPageEdit)
        val btn_myPageSave = findViewById<Button>(R.id.btn_myPageSave)
        btn_myPageSave.isInvisible = true


        val btn_withdrawl = findViewById<Button>(R.id.btn_withdrawl)

        val btn_back = findViewById<ImageView>(R.id.iv_backButton)

        myPageNickname.setText("${userNickname}")
        myPageStartupField.setText("${userStartupField}")
        myPagePassword.setText("${userPassword}")
        myPagePasswordCheck.setText("${userPasswordCheck}")

        btn_myPageEdit.setOnClickListener{
            btn_myPageEdit.isInvisible = true
            btn_myPageSave.isInvisible = false

            myPageNickname.isEnabled = true
            myPageStartupField.isEnabled = true
            myPagePassword.isEnabled = true
            myPagePasswordCheck.isEnabled = true

            btn_myPageSave.setOnClickListener{

                if (myPageNickname.text.isEmpty() == true || myPageStartupField.text.isEmpty() == true || myPagePassword.text.isEmpty() == true || myPagePasswordCheck.text.isEmpty()) {
                    Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                }

                if (userPassword != userPasswordCheck) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    userNickname = myPageNickname.text.toString()
                    userStartupField = myPageStartupField.text.toString()
                    userPassword = myPagePassword.text.toString()
                    userPasswordCheck = myPagePasswordCheck.text.toString()
                    Toast.makeText(this, "수정사항이 저장되었습니다.", Toast.LENGTH_SHORT).show()

                    btn_myPageEdit.isInvisible = false
                    btn_myPageSave.isInvisible = true

                    myPageNickname.isEnabled = false
                    myPageStartupField.isEnabled = false
                    myPagePassword.isEnabled = false
                    myPagePasswordCheck.isEnabled = false
                }
            }
        }

        btn_withdrawl.setOnClickListener{
            val withdrawlCheckPopup = findViewById<ConstraintLayout>(R.id.withdrawl_popup)
            withdrawlCheckPopup.setOnClickListener{
                showPopup()
            }
        }

        btn_back.setOnClickListener{
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

