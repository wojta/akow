package org.aerogear.akow.example.test.pageobjects.android

import org.aerogear.akow.example.test.pageobjects.MainScreen
import org.aerogear.akow.pageobject.AndroidScreen

/**
 * Created on 2/27/18.
 */
class MainAndroidScreen : AndroidScreen(), MainScreen {
    override val navDrawer by accessibilityId("Open navigation drawer")
    override val mnuTextFieldsItem by uiAutomator("new UiSelector().text(\"Textfields\")")
    override val mnuAccessors by uiAutomator("new UiSelector().text(\"Accessors\")")

}