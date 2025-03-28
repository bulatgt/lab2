package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity



class MainActivity : ComponentActivity() {
    private lateinit var imageView: ImageView
    private lateinit var descriptionTextView: TextView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button

    private val images = arrayOf(
        R.drawable.pic1 to "Донецк - Город Герой",
        R.drawable.pic2 to "Мемориальный комплекс 'Саур-Могила' после реставрации 2022",
        R.drawable.pic3 to "Мемориальный комплекс 'Освободители Донбасса'"
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config)

        imageView = findViewById(R.id.imageView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        prevButton = findViewById(R.id.prevButton)
        nextButton = findViewById(R.id.nextButton)

        savedInstanceState?.let {
            currentIndex = it.getInt("currentIndex", 0)
        }

        updateUI()

        prevButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }

        nextButton.setOnClickListener {
            if (currentIndex < images.size - 1) {
                currentIndex++
                updateUI()
            }
        }
    }

    private fun updateUI() {
        val (imageResId, description) = images[currentIndex]
        imageView.setImageResource(imageResId)
        descriptionTextView.text = description

        prevButton.visibility = if (currentIndex == 0) View.GONE else View.VISIBLE
        nextButton.visibility = if (currentIndex == images.size - 1) View.GONE else View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
    }
}