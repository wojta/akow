package org.aerogear.akow.dsl.base

import org.aerogear.akow.dsl.Appium


/**
 * Adds parent node to current node and executes its block.
 */
internal inline fun <reified T : Node> Node.addAsChild(parent: BaseNode, init: (T).() -> Unit): T = addAsChildIntoList(parent.children, init)

/**
 * Adds parent node to some list and executes its block.
 */
internal inline fun <reified T : Node> Node.addAsChildIntoList(list: MutableList<in Node>, init: (T).() -> Unit): T {
    list.add(this as T)
    init(this)
    return this
}

/**
 * Use this to initialize your test environment. Initialize it in some global variable, because you'll need it to initialize the tests.
 *
 * Example code:
 *```
 * val appium = appium("http://localhost:4723/wd/hub") {
 *                 applications {
 *                      androidApplication("org.aerogear.akow.example", ".MainActivity") {
 *                          desiredCapabilities {
 *                              ⋮
 *                          }
 *                          screens {
 *                              +MainAndroidScreen()
 *                              +TextFieldsAndroidScreen()
 *                              ⋮
 *                              ⋮
 *                          }
 *                      }
 *                      iosApplication(⋯) {
 *                          screens {
 *                              +MainIosScreen()
 *                              +TextFieldsIosScreen()
 *                          }
 *                      }
 *                      ⋮
 *                 }
 *              }
 *}
 *```
 */
fun appium(serverPath: String, init: Appium.() -> Unit): Appium {
    val appium = Appium(serverPath)
    init(appium)
    return appium
}

/**
 * DSL helper.
 */
@DslMarker
@MustBeDocumented
annotation class AkowNode