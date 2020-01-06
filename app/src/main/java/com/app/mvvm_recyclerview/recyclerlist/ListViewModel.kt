package com.app.mvvm_recyclerview.recyclerlist

import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mvvm_recyclerview.R
import com.app.mvvm_recyclerview.data.model.ErrorHandler
import com.app.mvvm_recyclerview.data.model.User
import com.app.mvvm_recyclerview.data.remote.FirebaseHandler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * @author Gautam Mittal
 * 27/12/19
 */
class ListViewModel(var firebaseHandler: FirebaseHandler):ViewModel()
{

    val items : ArrayList<User> = ArrayList()
    val list=MutableLiveData<ArrayList<User>>()
    var errorHandler=MutableLiveData<ErrorHandler>()
    val listEdit=MutableLiveData<User>()
    fun postfirebase()
    {
        firebaseHandler.getUserReference().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                items.clear()
                // Get Post object and use the values to update the UI
                for (postSnapshot in dataSnapshot.children) {
                    //getting artist
                    val dat = postSnapshot.getValue<User>(User::class.java)
                    //adding artist to the list
                    items.add(dat!!)
                }
                list.postValue(items)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }



    fun onDelete(item:User) {
        var id=item.token
        firebaseHandler.getUserReference().child(id).removeValue()
        errorHandler.value= ErrorHandler("delete",7)
        items.clear()

    }



    fun onEdit(data:User)
    {
        listEdit.postValue(data)
        errorHandler.value= ErrorHandler("edit",8)


    }


}