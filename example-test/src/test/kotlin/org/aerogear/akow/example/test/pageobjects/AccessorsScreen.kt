package org.aerogear.akow.example.test.pageobjects

import org.aerogear.akow.pageobject.PageObject
import org.openqa.selenium.WebElement

/**
 * Screen for accessors testing.
 */
interface AccessorsScreen : PageObject {
    val button1: WebElement
    val button2: WebElement
    val button3: WebElement
    val button3multiple: WebElement
    val button4: WebElement
}