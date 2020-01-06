package com.app.mvvm_recyclerview.addpost


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.app.mvvm_recyclerview.R
import com.app.mvvm_recyclerview.base.ViewModelFactory
import com.app.mvvm_recyclerview.data.model.ErrorHandler
import com.app.mvvm_recyclerview.data.model.User
import com.app.mvvm_recyclerview.data.remote.FirebaseHandler
import com.app.mvvm_recyclerview.databinding.FragmentAddPostBinding
import com.app.mvvm_recyclerview.recyclerlist.Adapter
import com.app.mvvm_recyclerview.recyclerlist.ListFragment
import com.app.mvvm_recyclerview.recyclerlist.ListViewModel
import com.google.android.gms.common.SupportErrorDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_post.*

/**
 * A simple [Fragment] subclass.
 */

class AddPostFragment : Fragment() {

    var mainBinding: FragmentAddPostBinding?=null
    private var addPostViewModel:AddPostViewModel?=null
    var viewModelFactory:ViewModelFactory?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mainBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_add_post, container, false)
        return mainBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModelFactory= ViewModelFactory(FirebaseHandler())

        addPostViewModel= ViewModelProviders.of(this,viewModelFactory).get<AddPostViewModel>(AddPostViewModel::class.java)
        mainBinding!!.lifecycleOwner = this
        mainBinding!!.addviewmodel = addPostViewModel


        addPostViewModel!!.error.observe(this,
            Observer <ErrorHandler>{ t->

                when(t!!.errorField)
                {
                    0->
                    {
                        Toast.makeText(context,t.errorMessage, Toast.LENGTH_LONG).show()
//                    startActivity()
                    }
                    1->
                    {
                        fragmentManager!!.beginTransaction().replace(R.id.fragment,ListFragment()).commit()
                        Toast.makeText(context,t.errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        )

    }

}