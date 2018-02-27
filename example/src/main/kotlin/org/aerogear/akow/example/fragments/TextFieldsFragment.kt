package org.aerogear.akow.example.fragments

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_text_fields.*
import kotlinx.android.synthetic.main.fragment_text_fields.view.*
import org.aerogear.akow.example.R

/**
 * Testing some textfields and buttons in there.
 */
class TextFieldsFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_text_fields

    override fun onAfterCreate(view: View, savedInstanceState: Bundle?) {
        super.onAfterCreate(view, savedInstanceState)
        view.btCopyText.setOnClickListener {
            txtField.text = edtText.text
        }
    }
}
