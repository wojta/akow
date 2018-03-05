package org.aerogear.akow.example.test.pageobjects

import org.aerogear.akow.pageobject.PageObject
import org.openqa.selenium.WebElement

/**
 * Created on 2/23/18.
 */
interface TextFieldsScreen : PageObject {
    val editText: WebElement
    val btCopyMe: WebElement
    val textField: WebElement
}