package org.aerogear.akow.dsl

import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.dsl.base.DesiredCapabilities
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.exceptions.CommandDuplicationException

/**
 * DSL class for Android application to be tested.
 */
class IosApplication(parent: Node) : Application(parent, "iosApplication") {

    var capInitCalled = false

    fun capabilities(init: DesiredCapabilities.() -> Unit) = IosDesiredCapabilities().also(init).also {
        if (!capInitCalled) {
            capInitCalled = true
        } else
            throw CommandDuplicationException("Don't use <capabilities> more than once.")
    }
}
