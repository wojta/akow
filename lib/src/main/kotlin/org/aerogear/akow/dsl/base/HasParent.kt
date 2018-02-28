package org.aerogear.akow.dsl.base

/**
 * Node used for building traversable tree representing Appium test configuration.
 */
interface HasParent : Node {
    var parent: Node

    override fun isRoot() = parent.isRoot()
}