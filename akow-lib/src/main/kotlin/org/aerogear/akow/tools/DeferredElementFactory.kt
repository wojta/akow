package org.aerogear.akow.tools

/**
 * Creates deffered elements and allows them to be requeried.
 */
internal class DeferredElementFactory {

    private val elements = mutableListOf<DeferredElement>()

    /**
     * Creates new instance of [DeferredElement].
     */
    fun create(findStrategy: ElementFindStrategy, desc: String): DeferredElement {
        val element = DeferredElement(ElementFindStrategyDesc(findStrategy, desc))
        elements += element
        return element
    }

    /**
     * Requeries all elements.
     */
    fun requery() {
        elements.forEach { it.requery() }
    }
}
