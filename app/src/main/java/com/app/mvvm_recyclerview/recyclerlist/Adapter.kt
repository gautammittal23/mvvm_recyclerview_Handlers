package com.app.mvvm_recyclerview.recyclerlist

import android.content.Context
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvm_recyclerview.BR
import com.app.mvvm_recyclerview.R
import com.app.mvvm_recyclerview.addpost.AddPostFragment
import com.app.mvvm_recyclerview.data.model.User
import com.app.mvvm_recyclerview.databinding.FragmentListBinding
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.userlist.view.*

/**
 * @author Gautam Mittal
 * 27/12/19
 */
class Adapter(var viewModel: ListViewModel) : RecyclerView.Adapter<Adapter.ViewHolder>()
{

    val items : ArrayList<User> = ArrayList()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val binding:ViewDataBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.userlist,parent,false)
        return ViewHolder (binding,viewModel)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(items[position])

        Log.d("sh",items[position].toString())

    }



    override fun getItemCount(): Int {

        return items.size

    }
    class ViewHolder (var mbinding:ViewDataBinding,var viewModel: ListViewModel) : RecyclerView.ViewHolder(mbinding.root)
    {

        //val textofviewholder = view.txtdescription


        fun bindItems(user: User)
        {
            mbinding.setVariable(BR.viewModel,viewModel)
          //  mbinding.setVariable(BR.t,viewModel)
            mbinding.setVariable(BR.data,user)
            mbinding.executePendingBindings()
        }


    }



    fun onUpdateAll(data:List<User>)
    {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}
