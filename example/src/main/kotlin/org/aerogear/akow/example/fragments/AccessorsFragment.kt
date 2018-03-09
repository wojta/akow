package org.aerogear.akow.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import org.aerogear.akow.example.R

/**
 * Testing some textfields and buttons in there.
 */
class AccessorsFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_accessors

    override fun onAfterCreate(view: View, savedInstanceState: Bundle?) {
        super.onAfterCreate(view, savedInstanceState)
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                view.getChildAt(i).setOnClickListener(::accessorClicked)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun accessorClicked(v: View) {
        val CLICKED = "CLICKED"
        when (v) {
            is Button -> {
                v.text = CLICKED
            }
            is ImageButton -> {
                v.contentDescription = CLICKED
            }
        }
    }

}
