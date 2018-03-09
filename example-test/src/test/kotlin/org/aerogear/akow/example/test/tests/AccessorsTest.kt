import org.aerogear.akow.example.test.base.BaseTest
import org.aerogear.akow.example.test.pageobjects.AccessorsScreen
import org.aerogear.akow.example.test.pageobjects.MainScreen
import org.aerogear.akow.example.test.pageobjects.android.AndroidAccessorsScreen.Companion.ACCESSIBILITY_ID
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

/**
 * Tests Akow on accessing text fields.
 */
@RunWith(Parameterized::class)
class AccessorsTest : BaseTest() {

    @Test
    fun testAccessors() {
        val CLICKED = "CLICKED"

        akow {

            on<MainScreen> {
                selectInNavDrawer(mnuAccessors)
            }
            on<AccessorsScreen> {
                button1.click()
                assertEquals(CLICKED, button1.text)
                button2.click()
                assertEquals(CLICKED, button2.text)
                androidOnly {
                    assertEquals(ACCESSIBILITY_ID, button3.getAttribute("contentDescription"))
                    button3multiple.click()
                    assertEquals(CLICKED, button3multiple.getAttribute("contentDescription"))
                }
                button4.click()
                assertEquals(CLICKED, button4.text)
            }
        }
    }
}