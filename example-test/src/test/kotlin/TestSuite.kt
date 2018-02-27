import org.junit.runner.RunWith
import org.junit.runners.Suite
import tests.TextfieldsTests
import tests.old.FirstTest


@RunWith(Suite::class)
@Suite.SuiteClasses(
        FirstTest::class,
        TextfieldsTests::class
)
class TestSuite