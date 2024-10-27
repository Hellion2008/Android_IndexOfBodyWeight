package ru.urban.android_indexofbodyweight

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var heightEditTextET: EditText
    private lateinit var weightEditTextET: EditText

    private lateinit var calculatedBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        heightEditTextET = findViewById(R.id.heightEditTextET)
        weightEditTextET = findViewById(R.id.weightEditTextET)

        calculatedBTN = findViewById(R.id.calculatedBTN)

        calculatedBTN.setOnClickListener {view ->
            if (heightEditTextET.text.isEmpty() || weightEditTextET.text.isEmpty()) return@setOnClickListener

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(
                "result",
                calculateIndex(heightEditTextET.text.toString().toDouble(), weightEditTextET.text.toString().toDouble()))
            startActivity(intent)
        }
    }

    private fun calculateIndex (height: Double, weight: Double): Double{
        return weight / (height / 100 * height / 100)
    }
}