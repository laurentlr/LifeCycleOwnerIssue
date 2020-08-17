package com.example.lifecycleowner.issue

import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val myViewModel = MyViewModel()

    override fun onStart() {
        super.onStart()
        val linear = findViewById<LinearLayout>(R.id.linear)

        myViewModel
            .state
            .observe(this, Observer { //Works when observing for ever
                linear.addView(TextView(this).apply { text = it })
                //only last emitted element is catch
                //Result is: "second"
                //Expected: "first, second"
            })

        myViewModel.initialize()

    }


}

