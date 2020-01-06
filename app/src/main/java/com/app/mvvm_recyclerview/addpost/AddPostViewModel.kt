package com.app.mvvm_recyclerview.addpost

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mvvm_recyclerview.data.model.User
import com.app.mvvm_recyclerview.data.remote.FirebaseHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.xml.sax.ErrorHandler

/**
 * @author Gautam Mittal
 * 28/12/19
 */
class AddPostViewModel(var firebaseHandler: FirebaseHandler):ViewModel() {


    var name=MutableLiveData<String>()
    var desc=MutableLiveData<String>()

    var error=MutableLiveData<com.app.mvvm_recyclerview.data.model.ErrorHandler>()




    var firebaseDatabase = firebaseHandler.getUserReference().push()


    init {
        name.value=""
        desc.value=""
    }


    fun onsave(view:View)
    {
        if (TextUtils.isEmpty(name.value))
        {
            error.value = com.app.mvvm_recyclerview.data.model.ErrorHandler(
                "Enter name",
                0
            )
        }
        else if(TextUtils.isEmpty(desc.value))
        {
            error.value = com.app.mvvm_recyclerview.data.model.ErrorHandler(
                "Enter description",
                0
            )
        }

        else
        {
            var uid=firebaseDatabase.key

            // here i will perform firebase push
           var user:User=User()
            user.token=uid.toString()
            user!!.name=name.value.toString()
            user!!.desc=desc.value.toString()



            firebaseDatabase.setValue(user)




         //   startActivity(Intent(this,TODOActivity::class.java))


            error.value = com.app.mvvm_recyclerview.data.model.ErrorHandler(
                "Data Saved in FireBase", 1
            )
        }

    }

}