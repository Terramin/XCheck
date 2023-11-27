package com.xcheckinc.xcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linkToAuth: TextView = findViewById(R.id.linkToAuth)

        linkToAuth.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        val formUserPassword = findViewById<EditText>(R.id.edittextPassword)
        val formUserLogin = findViewById<EditText>(R.id.edittextLogin)
        val formRegButton = findViewById<Button>(R.id.regButton)

        formRegButton.setOnClickListener{
            val UserPassword = formUserPassword.text.toString().trim()
            val UserLogin = formUserLogin.text.toString().trim()

            if (UserPassword == "" || UserLogin == ""){
                Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_LONG).show()
            } else {
                val user = User(UserLogin, UserPassword)

                val db = DBHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $UserLogin Добавлен", Toast.LENGTH_SHORT).show()

                formUserLogin.text.clear()
                formUserPassword.text.clear()
            }
        }
    }
}