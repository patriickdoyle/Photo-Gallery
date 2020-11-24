package com.example.photogallery

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.photogallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //create binding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //replace setContentView() with binding object
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //create map of photos and associated text
        val galleryMap = mapOf(binding.text1 to binding.photo1,
            binding.text2 to binding.photo2, binding.text3 to binding.photo3,
            binding.text4 to binding.photo4, binding.text5 to binding.photo5,
            binding.text6 to binding.photo6, binding.text7 to binding.photo7,
            binding.text8 to binding.photo8, binding.text9 to binding.photo9,
            binding.text10 to binding.photo10)

        //create a list of photo descriptions
        val descriptionList = listOf(binding.text1, binding.text2,
            binding.text3, binding.text4, binding.text5, binding.text6, binding.text7,
            binding.text8, binding.text9, binding.text10)

        //create pointer to keep track of current photo position (initially 0)
        var current = 0

        //set the app to open to photo1 and text1
        descriptionList[current].visibility = View.VISIBLE
        (galleryMap[descriptionList[current]] ?: error("")).visibility = View.VISIBLE

        //call next() when the nextButton is pressed
        binding.nextButton.setOnClickListener{

            //hide the current photo and description
            descriptionList[current].visibility = View.GONE
            (galleryMap[descriptionList[current]] ?: error("")).visibility = View.GONE

            //increment the value of current
            //conditional will ensure the end of the gallery will loop back
            //to the start
            if(current == 9) { current = 0 }
            else { current++}

            //set the current photo and description to visible
            descriptionList[current].visibility = View.VISIBLE
            (galleryMap[descriptionList[current]] ?: error("")).visibility = View.VISIBLE
        }

        //call previous() when previousButton is pressed
        binding.previousButton.setOnClickListener {
            //hide the current photo and description
            descriptionList[current].visibility = View.GONE
            (galleryMap[descriptionList[current]] ?: error("")).visibility = View.GONE

            //increment the value of current
            //conditional will ensure the start of the gallery will loop back
            //to the end
            if(current == 0) { current = 9 }
            else { current--}

            //set the current photo and description to visible
            descriptionList[current].visibility = View.VISIBLE
            (galleryMap[descriptionList[current]] ?: error("")).visibility = View.VISIBLE
        }
    }
}