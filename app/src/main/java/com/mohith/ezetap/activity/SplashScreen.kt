package com.mohith.ezetap.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.mohith.ezetap.R
import de.dlyt.yanndroid.oneui.layout.SplashView

class SplashScreen : AppCompatActivity() {
    private var launchCanceled = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashView: SplashView = findViewById(R.id.splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ splashView.startSplashAnimation() }, 500)

        splashView.setSplashAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                if (!launchCanceled) launchApp()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    private fun launchApp() {
        startActivity(Intent().setClass(applicationContext, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    override fun onPause() {
        super.onPause()
        launchCanceled = true
    }

    override fun onResume() {
        super.onResume()
        if (launchCanceled) launchApp()
    }
}