package org.aerogear.akow.example.test

import org.aerogear.akow.dsl.base.appium
import org.aerogear.akow.example.test.pageobjects.android.AndroidAccessorsScreen
import org.aerogear.akow.example.test.pageobjects.android.MainAndroidScreen
import org.aerogear.akow.example.test.pageobjects.android.TextFieldsAndroidScreen

val appium = appium(BuildConfig.APPIUM_SERVER_URL) {
    applications {
        androidAPKApplication(BuildConfig.TEST_APK, "org.aerogear.akow.example") {
            //androidApplication("org.aerogear.akow.example", ".MainActivity") {
            capabilities {
            }
            screens {
                +MainAndroidScreen()
                +TextFieldsAndroidScreen()
                +AndroidAccessorsScreen()

            }
        }

    }
}