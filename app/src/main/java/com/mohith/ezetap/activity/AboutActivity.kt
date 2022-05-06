package com.mohith.ezetap.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.mohith.ezetap.R
import com.mohith.ezetap.uitls.Const

class AboutActivity : AppCompatActivity() {

    private lateinit var phoneCall :MaterialButton
    private lateinit var playStore :MaterialButton
    private lateinit var likedIn :MaterialButton
    private lateinit var gitHub :MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        this.bindIDs()
        this.setOnClick()
    }

    private fun setOnClick() {
        this.phoneCall.setOnClickListener {
            openPhone(Const.phoneNumber)
        }
        this.playStore.setOnClickListener {
            openOnBrowser(Const.playStoreUrl)
        }
        this.likedIn.setOnClickListener {
            openOnBrowser(Const.linkedInUrl)
        }
        this.gitHub.setOnClickListener {
            openOnBrowser(Const.githubUrl)
        }
    }

    private fun openPhone(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+"+number)
        startActivity(intent)

    }

    private fun bindIDs() {
        phoneCall = findViewById(R.id.phone)
        playStore =findViewById(R.id.play_store)
        likedIn = findViewById(R.id.linked_in)
        gitHub = findViewById(R.id.github)
    }


    private fun openOnBrowser(url:String){
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}