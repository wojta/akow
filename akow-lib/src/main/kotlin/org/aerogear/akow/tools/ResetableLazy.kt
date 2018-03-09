package org.aerogear.akow.tools


/**
 * Implementation similar to common lazy delegate, but with ability to be resetted.
 */
private object UNINITIALIZED_VALUE

/**
 * Implementation similar to common lazy() delegate, but with ability to be reset. Non-thread safe!!
 */
fun <T> resetableLazy(initializer: () -> T): Lazy<T> = ResetableLazy(initializer)

/**
 * Implementation similar to common lazy delegate, but with ability to be resetted.
 */
class ResetableLazy<out T>(initializer: () -> T) : Lazy<T> {
    private var initializer: (() -> T)? = initializer
    private var _value: Any? = UNINITIALIZED_VALUE

    override val value: T
        get() {
            if (_value === UNINITIALIZED_VALUE) {
                _value = initializer!!()
                initializer = null
            }
            @Suppress("UNCHECKED_CAST")
            return _value as T
        }

    override fun isInitialized(): Boolean = _value !== UNINITIALIZED_VALUE

    override fun toString(): String = if (isInitialized()) value.toString() else "Lazy value not initialized yet."

    /**
     * Sets the value to be reinitialized on the next access.
     */
    fun reset() {
        _value = UNINITIALIZED_VALUE
    }

}