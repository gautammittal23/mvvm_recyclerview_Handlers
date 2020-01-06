package com.app.mvvm_recyclerview.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mvvm_recyclerview.addpost.AddPostViewModel
import com.app.mvvm_recyclerview.data.remote.FirebaseHandler
import com.app.mvvm_recyclerview.recyclerlist.ListViewModel

/**
 * @author Gautam Mittal
 * 4/1/20
 */
class ViewModelFactory(var firebaseHandler: FirebaseHandler):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddPostViewModel::class.java))
        {
            return AddPostViewModel(firebaseHandler) as T
        }
        else if(modelClass.isAssignableFrom(ListViewModel::class.java))
        {
            return ListViewModel(firebaseHandler) as T
        }
        throw IllegalAccessException("Unknown class")
    }
}