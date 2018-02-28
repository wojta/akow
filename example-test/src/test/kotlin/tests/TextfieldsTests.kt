package tests

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import pageobjects.MainScreen
import pageobjects.TextFieldsScreen
import tests.base.BaseTest
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