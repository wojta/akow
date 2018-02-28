import org.aerogear.akow.dsl.base.appium
import pageobjects.android.MainAndroidScreen
import pageobjects.android.TextFieldsAndroidScreen


val appium = appium("http://localhost:4723/wd/hub") {
    applications {
        androidApplication("org.aerogear.akow.example", ".MainActivity") {
            capabilities {
            }
            screens {
                +MainAndroidScreen()
                +TextFieldsAndroidScreen()

            }
        }

    }
}