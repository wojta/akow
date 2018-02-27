package pageobjects.android

import org.aerogear.akow.pageobject.AndroidScreen
import pageobjects.TextFieldsScreen

/**
 * Created on 2/23/18.
 */
class TextFieldsAndroidScreen : AndroidScreen(), TextFieldsScreen {
    override val editText by id("org.aerogear.akow.example:id/edtText")
    override val btCopyMe by id("org.aerogear.akow.example:id/btCopyText")
    override val textField by id("org.aerogear.akow.example:id/txtField")
}