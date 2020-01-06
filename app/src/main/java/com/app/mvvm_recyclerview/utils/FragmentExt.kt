package com.app.mvvm_recyclerview.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @author Gautam Mittal
 * 4/1/20
 */


fun Fragment.showToast(message:String)
{
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}