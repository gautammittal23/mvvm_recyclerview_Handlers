package com.app.mvvm_recyclerview.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * @author Gautam Mittal
 * 4/1/20
 */
class FirebaseHandler {

    val db=FirebaseDatabase.getInstance()

    companion object
    {
        var uid=FirebaseAuth.getInstance().uid
    }

    private val mFirebaseAuth:FirebaseAuth by lazy { FirebaseAuth.getInstance() }


    fun getUserReference():DatabaseReference{
        return db.getReference("user")
    }





    // Instance of current user
    fun currentUser(){
        mFirebaseAuth.currentUser
    }


    // get current user


    fun getUser():String
    {
        return mFirebaseAuth.currentUser?.uid.toString()
    }

}