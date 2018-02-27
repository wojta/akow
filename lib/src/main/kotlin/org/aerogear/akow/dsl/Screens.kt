package org.aerogear.akow.dsl

import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.dsl.base.BaseNode
import org.aerogear.akow.dsl.base.Node
import org.aerogear.akow.exceptions.CommandDuplicationException
import org.aerogear.akow.pageobject.PageObject
import kotlin.reflect.KClass

/**
 * DSL for adding page objects to the [Application].
 * ```
 * screens {
 *   +MainAndroidScreen()
 *   +TextFieldsAndroidScreen()
 *   ⋮
 *   ⋮
 * }
 * ```
 * @see appium
 */
class Screens : BaseNode("screens") {
    override val children = mutableListOf<Node>()
    override var parent: Node = Node.ROOT
    private val screenDuplicityCheckSet = mutableSetOf<KClass<out PageObject>>()

    /**
     * Adding [PageObject] to the list of screens.
     */
    operator fun PageObject.unaryPlus() {
        this@Screens.children.add(this@unaryPlus)
        this@unaryPlus.parent = this@Screens
        val cls = this@unaryPlus::class
        if (cls in this@Screens.screenDuplicityCheckSet) throw CommandDuplicationException("${cls.simpleName} is already added in <screens>")
        this@Screens.screenDuplicityCheckSet.add(cls)
    }
}