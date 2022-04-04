package com.rivanov.snackbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class SnackbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        val snackButton = findViewById<Button>(R.id.snack_me_button)
        val snackLayout = findViewById<CoordinatorLayout>(R.id.snackbar_container)

        val snack = Snackbar.make(snackLayout, "tasty", Snackbar.LENGTH_INDEFINITE);
        snack.setAction("Yes please!") {
            snack.dismiss()
        }

        snack.setBackgroundTint(Color.BLACK)
        val snacksList = ArrayList<String>()
        snacksList.add("Banana")
        snacksList.add("Chips")
        snacksList.add("Waffle")

        val random = Random(1)
        snack.setTextColor(Color.GREEN)



        snackButton.setOnClickListener {
            snack.setText("Care for a tasty " + snacksList.get(random.nextInt(snacksList.size)))
            snack.show()
        }
    }
}