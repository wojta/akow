package org.aerogear.akow.example.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created on 2/23/18.
 */
abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int

    final override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(layoutId, container, false)
        onAfterCreate(view, savedInstanceState)
        return view
    }

    open fun onAfterCreate(view: View, savedInstanceState: Bundle?) {

    }

}