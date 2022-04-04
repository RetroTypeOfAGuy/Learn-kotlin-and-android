package com.rivanov.toast

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class ToastActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)

        val clickMeButton = findViewById<Button>(R.id.click_me)
        val textField = findViewById<TextView>(R.id.i_am_text)
        textField.textSize = 20f

        val toast = Toast.makeText(this, "Hi im toast", Toast.LENGTH_LONG)

        val callback = object : Toast.Callback() {
            override fun onToastShown() {
                textField.visibility = View.VISIBLE
                textField.setText(R.string.toast_show)
            }

            override fun onToastHidden() {
                textField.setText(R.string.toast_hide)
            }
        }

        toast.addCallback(callback)
        clickMeButton.setOnClickListener { toast.show() }
    }
}