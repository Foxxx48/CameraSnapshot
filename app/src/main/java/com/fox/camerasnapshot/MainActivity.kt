package com.fox.camerasnapshot

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.fox.camerasnapshot.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val PIC_ID = 123

    val myStartForResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback {
            val photo = it?.data?.extras?.get("data") as Bitmap?
            println(photo)
            binding.clickImage.setImageBitmap(photo)
        })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraButton.setOnClickListener {
            val cameraIntent = Intent(
                MediaStore
                .ACTION_IMAGE_CAPTURE)

                myStartForResultLauncher.launch(cameraIntent)

        }
    }
}