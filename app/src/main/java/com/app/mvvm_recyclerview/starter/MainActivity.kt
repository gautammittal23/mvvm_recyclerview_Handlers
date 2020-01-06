package com.app.mvvm_recyclerview.starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.mvvm_recyclerview.R
import com.app.mvvm_recyclerview.addpost.AddPostFragment
import com.app.mvvm_recyclerview.addpost.AddPostViewModel
import com.app.mvvm_recyclerview.recyclerlist.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().replace(R.id.fragment,AddPostFragment()).commit()
    }
}
