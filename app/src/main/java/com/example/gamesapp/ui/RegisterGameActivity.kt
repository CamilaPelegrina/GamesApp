package com.example.gamesapp.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamesapp.R
import com.example.gamesapp.model.Game
import com.example.gamesapp.model.RegisterViewModel
import com.example.gamesapp.model.RepositoryDatabase
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_register_game.*

class RegisterGameActivity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference
    private val CODE_IMG = 1000
    lateinit var urlImage: String

    private val repository = RepositoryDatabase()
    private val viewModel by viewModels<RegisterViewModel> {
        object : ViewModelProvider.Factory {
            override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
                return com.example.gamesapp.model.RegisterViewModel(repository) as VM
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_game)

        config()

        imageView.setOnClickListener{
            setIntent()
        }

        btnSave.setOnClickListener{
            saveGame()
        }
    }

    fun saveGame() {
        val newTitle = findViewById<TextInputEditText>(R.id.edTitle)
        val newYear = findViewById<TextInputEditText>(R.id.edYear)
        val newOverview = findViewById<TextInputEditText>(R.id.edOverview)


        viewModel.connectDatabase()
        viewModel.insertGameDatabase(Game(
                urlImage,
                newTitle.text.toString(),
                newYear.text.toString(),
                newOverview.text.toString()
            )
        )

        Toast.makeText(this, "Game saved", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun setIntent(){
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "capture image"), CODE_IMG)
    }

    fun config() {
        alertDialog = SpotsDialog.Builder().setContext(this).build()
        storageReference = FirebaseStorage.getInstance().getReference("prod_img")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        viewModel.startReferenceStorage()
        viewModel.storageReference.observe(this) {
            storageReference = it

            if (requestCode == CODE_IMG) {
                alertDialog.show()
                val uploadTask = storageReference.putFile(data!!.data!!)
                val task = uploadTask.continueWithTask { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Image uploaded", Toast.LENGTH_SHORT).show()
                    }
                    storageReference!!.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        urlImage = downloadUri!!.toString()
                            .substring(0, downloadUri.toString().indexOf("&token"))
                        Log.i("Reference URL: ", urlImage)
                        alertDialog.dismiss()
//                    Picasso.get().load(url).into(imageView)
                        Picasso.get().load(urlImage).resize(150, 150)
                            .placeholder(R.drawable.ic_baseline_blur_on_24).into(imageView)
                    }
                }
            }
        }
    }
}