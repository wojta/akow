package org.aerogear.akow.dsl

import org.aerogear.akow.dsl.base.Node

/**
 * Top level DSL class for Appium configuration.
 */
class Appium(var serverPath: String) : Node {
    override val nodeName = "appium"

    lateinit var applications: Applications
        internal set

    fun applications(applications: Applications.() -> Unit): Applications = Applications(this).also(applications).also {
        this.applications = it
    }

}

