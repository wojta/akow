package org.aerogear.akow.example.test.pageobjects.android

import org.aerogear.akow.example.test.pageobjects.TextFieldsScreen
import org.aerogear.akow.pageobject.AndroidScreen

/**
 * Created on 2/23/18.
 */
class TextFieldsAndroidScreen : AndroidScreen(), TextFieldsScreen {
    override val editText by id("org.aerogear.akow.example:id/edtText")
    override val btCopyMe by id("org.aerogear.akow.example:id/btCopyText")
    override val textField by id("org.aerogear.akow.example:id/txtField")
}