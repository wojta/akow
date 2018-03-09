package org.aerogear.akow.example.test.pageobjects.android

import org.aerogear.akow.example.test.pageobjects.AccessorsScreen
import org.aerogear.akow.pageobject.AndroidScreen


class AndroidAccessorsScreen : AccessorsScreen, AndroidScreen() {
    companion object {
        const val ACCESSIBILITY_ID = "Image button with accessibility"
        const val CLICKED = "CLICKED"
    }

    override val button1 by id("org.aerogear.akow.example:id/btIdAccessor")
    override val button2 by xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.Button[2]")
    override val button3 by accessibilityId(ACCESSIBILITY_ID)
    override val button3multiple by multiple(accessibilityId(ACCESSIBILITY_ID), accessibilityId(CLICKED))
    override val button4 by uiAutomator("new UiSelector().resourceId(\"org.aerogear.akow.example:id/btUiAutomator\")")
}