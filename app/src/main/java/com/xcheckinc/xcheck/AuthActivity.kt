package com.xcheckinc.xcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val formUserPasswordAuth = findViewById<EditText>(R.id.edittextPasswordAuth)
        val formUserLoginAuth = findViewById<EditText>(R.id.edittextLoginAuth)
        val formAuthButton = findViewById<Button>(R.id.authButton)
        val linkToReg: TextView = findViewById(R.id.linkToReg)

        formAuthButton.setOnClickListener{
            val UserPassword = formUserPasswordAuth.text.toString().trim()
            val UserLogin = formUserLoginAuth.text.toString().trim()

            if (UserPassword == "" || UserLogin == ""){
                Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_LONG).show()
            } else {

                val db = DBHelper(this, null)
                val isAuth = db.GetUser(UserLogin, UserPassword)

                if(isAuth){
                    Toast.makeText(this, "Пользователь $UserLogin Авторизован", Toast.LENGTH_SHORT).show()
                    formUserLoginAuth.text.clear()
                    formUserPasswordAuth.text.clear()
                }else{
                    Toast.makeText(this, "Пользователь $UserLogin Не Авторизован", Toast.LENGTH_SHORT).show()
                }

            }
        }

        linkToReg.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}