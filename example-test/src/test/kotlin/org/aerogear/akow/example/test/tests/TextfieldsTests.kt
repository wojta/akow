package org.aerogear.akow.example.test.tests

import org.aerogear.akow.example.test.base.BaseTest
import org.aerogear.akow.example.test.pageobjects.MainScreen
import org.aerogear.akow.example.test.pageobjects.TextFieldsScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

/**
 * Tests Akow on accessing text fields.
 */
@RunWith(Parameterized::class)
class TextfieldsTests : BaseTest() {

    @Test
    fun copyMeTest() {
        akow {

            on<MainScreen> {
                selectInNavDrawer(mnuTextFieldsItem)
            }

            on<TextFieldsScreen> {
                val toBeCopied = "Hello akow!"
                editText.click()
                editText.sendKeys(toBeCopied)
                btCopyMe.click()
                assertEquals(toBeCopied, textField.text)
            }

        }
    }
}