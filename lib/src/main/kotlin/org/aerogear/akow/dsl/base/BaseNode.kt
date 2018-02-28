package org.aerogear.akow.dsl.base

/**
 * Base node for all Akow Apium configuration DSL.
 */
abstract class BaseNode(override val nodeName: String) : HasParent {
    internal abstract val children: MutableList<in Node>
}