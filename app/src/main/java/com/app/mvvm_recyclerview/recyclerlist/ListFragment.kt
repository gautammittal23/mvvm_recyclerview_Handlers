package com.app.mvvm_recyclerview.recyclerlist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvm_recyclerview.R
import com.app.mvvm_recyclerview.base.ViewModelFactory
import com.app.mvvm_recyclerview.data.model.User
import com.app.mvvm_recyclerview.data.remote.FirebaseHandler
import com.app.mvvm_recyclerview.databinding.FragmentListBinding
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.alert_input_layout.view.*
import org.xml.sax.ErrorHandler

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    var item = User()

    val items: ArrayList<User> = ArrayList()

    var mbinding: FragmentListBinding? = null
    var mlistviewmodel: ListViewModel? = null

    var mlistviewmodeladapter: Adapter? = null
    var errorHandler: ErrorHandler? = null
    var viewModelFactory:ViewModelFactory?=null

    var firebaseDatabase = FirebaseDatabase.getInstance().getReference("user")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return mbinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory= ViewModelFactory(FirebaseHandler())
        mlistviewmodel = ViewModelProviders.of(this,viewModelFactory).get<ListViewModel>(ListViewModel::class.java)

        mlistviewmodeladapter = Adapter(mlistviewmodel!!)

        mbinding!!.lifecycleOwner = this
        mbinding!!.listviewmodel = mlistviewmodel

        mlistviewmodel!!.postfirebase()


        observelivedata()


        /*
            *
            *
            * Below Observer is for the delete and edit
            *
            * * */

        mlistviewmodel!!.errorHandler.observe(this,
            Observer { t ->

                when (t!!.errorField) {
                    7 -> {
                        mlistviewmodeladapter!!.notifyDataSetChanged()
                    }
                    8 -> {
                        mlistviewmodel!!.listEdit.observe(viewLifecycleOwner, Observer {
                            Toast.makeText(context, "${it.name}", Toast.LENGTH_SHORT).show()


                            alert(it.token)
                        })

                    }

                }
            }
        )




        mbinding!!.recycler.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mbinding!!.recycler.adapter = mlistviewmodeladapter

    }

    fun observelivedata() {
        mlistviewmodel!!.list.observe(viewLifecycleOwner, Observer {
            mlistviewmodeladapter!!.onUpdateAll(it)
        })
    }


    fun alert(token: String) {
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.alert_input_layout, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(context!!)
            .setView(mDialogView)
            .setTitle("Enter your new updated value ")
        //show dialog
        val mAlertDialog = mBuilder.show()
        //Edit button click of custom layout
        mDialogView.btnEditAlert.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            val name = mDialogView.editTextTitle.text.toString()
            val desc = mDialogView.editTextDesc.text.toString()
            //set the input text in Model object
            var item = User(name, desc, token)


            firebaseDatabase.child(token).setValue(item)

        }

    }


}
