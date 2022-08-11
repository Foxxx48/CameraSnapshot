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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myStartForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {

//                if (it.resultCode == PIC_ID) {

                    val photo = it?.data?.extras?.get("data") as Bitmap?
                    println(photo)


                    binding.clickImage.setImageBitmap(photo)
//                }
            })

        binding.cameraButton.setOnClickListener {



            var cameraIntent = Intent(
                MediaStore
                .ACTION_IMAGE_CAPTURE)

                myStartForResult.launch(cameraIntent)
//            startActivityForResult(cameraIntent, PIC_ID)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        // Match the request 'pic id with requestCode
//        if (requestCode == PIC_ID) {
//
//            // BitMap is data structure of image file
//            // which stor the image in memory
//            val photo = data?.extras?.get("data") as Bitmap?
//
//            // Set the image in imageview for display
//            binding.clickImage.setImageBitmap(photo)
//        }
//    }
}