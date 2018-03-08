package org.aerogear.akow.dsl.base

/**
 * Created on 2/14/18.
 */
@AkowNode
interface Node {

    val nodeName: String

    fun isRoot() = this === ROOT

    companion object {
        val ROOT = object : Node {
            override val nodeName: String = "ROOT"
        }
    }
}