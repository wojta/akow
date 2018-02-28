package org.aerogear.akow.dsl

import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.exceptions.CommandDuplicationException

/**
 * DSL class for Android application to be tested.
 *
 * ```
 * androidApplication("org.aerogear.akow.example", ".MainActivity") {
 *   capabilities {
 *   }
 *   screens {
 *
 *   }
 * }
 * ```
 */
class AndroidApplication(parent: Node) : Application(parent, "androidApplication") {

    var capInitCalled = false

    var mainActivity: String? = ""

    init {
        capabilities = AndroidDesiredCapabilities()
    }

    fun capabilities(init: AndroidDesiredCapabilities.() -> Unit): AndroidDesiredCapabilities = AndroidDesiredCapabilities().also {
        if (!capInitCalled) {
            this.capabilities = it
            capInitCalled = true
        } else
            throw CommandDuplicationException("Don't use <capabilities> more than once.")
    }.also(init)


}
