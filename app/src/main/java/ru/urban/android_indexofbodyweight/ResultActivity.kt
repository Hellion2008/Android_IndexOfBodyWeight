package ru.urban.android_indexofbodyweight

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var resultTV: TextView

    private lateinit var resultImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTV = findViewById(R.id.resultTV)
        resultImg = findViewById(R.id.resultImg)

        val info = Information()

        val resultIndex: Double = intent.getDoubleExtra("result", 0.0)
        when{
            resultIndex < 18.5 -> {
                setInfo(info.thin, R.drawable.thin)
            }
            resultIndex >= 18.5 && resultIndex < 25 -> {
                setInfo(info.normal, R.drawable.normal)
            }
            resultIndex >= 25 -> {
                setInfo(info.fat, R.drawable.fat)
            }
            else -> resultTV.text = "Неверные данные"

        }
    }

    private fun setInfo(text: String, imgRes: Int) {
        resultTV.text = text
        resultImg.setImageResource(imgRes)
    }
}