import org.aerogear.akow.example.test.base.BaseTest
import org.aerogear.akow.example.test.pageobjects.MainScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Tests Akow on accessing text fields.
 */
@RunWith(Parameterized::class)
class EmptyTest : BaseTest() {

    @Test
    fun doSomething() {
        akow {

            on<MainScreen> {
                selectInNavDrawer(mnuTextFieldsItem)
            }

        }
    }
}