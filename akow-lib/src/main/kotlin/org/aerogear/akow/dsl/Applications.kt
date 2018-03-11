package org.aerogear.akow.dsl

import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.dsl.base.BaseNode
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.dsl.base.addAsChild

/**
 * DSL class for adding applications to be tested.
 */
class Applications(override var parent: Node) : BaseNode("applications") {

    override val nodeName = "applications"

    override val children = mutableListOf<Node>()

    fun asList(): List<Application> = this.children.filterIsInstance<Application>()

    val appium = parent as Appium

    /**
     * Adds Android application with [packageName] APK on [apkPath] to be tested. App will be installed on the device first.
     */
    fun androidAPKApplication(apkPath: String?, packageName: String, activity: String, init: AndroidApplication.() -> Unit) = AndroidApplication(this).also {
        it.appPackage = packageName
        it.appActivity = activity
        it.appPath = apkPath
    }.addAsChild(this, init)

    /**
     * Adds Android application with [packageName] to be tested and starts its [activity], App needs to be already installed on the device.
     */
    fun androidApplication(packageName: String, activity: String, init: AndroidApplication.() -> Unit): AndroidApplication = AndroidApplication(this).apply {
        appPackage = packageName
        appPath = null
        appActivity = activity
    }.addAsChild(this, init)

    /**
     * Adds iOS application to be tested.
     */
    fun iosApplication(init: IosApplication.() -> Unit) = IosApplication(this).addAsChild(this, init).also {
        throw UnsupportedOperationException("iOS Applications not yet supported")
    }

}