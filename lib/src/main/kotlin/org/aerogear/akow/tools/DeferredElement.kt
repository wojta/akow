package org.aerogear.akow.tools

import org.openqa.selenium.*


typealias ElementFindStrategy = () -> WebElement

/**
 * This class allows deferred resolution of elements on the page. So they can be passed in arguments.
 */
class DeferredElement(findStrategy: ElementFindStrategy) : WebElement {

    /**
     * Wrapped element, accessing it will cause it being searched on the page.
     */
    val element: WebElement by lazy {
        findStrategy()
    }

    /**
     * Is this element displayed or not? This method avoids the problem of having to parse an
     * element's "style" attribute.
     *
     * @return Whether or not the element is displayed
     */
    override fun isDisplayed(): Boolean = element.isDisplayed

    /**
     * If this element is a text entry element, this will clear the value. Has no effect on other
     * elements. Text entry elements are INPUT and TEXTAREA elements.
     *
     * Note that the events fired by this event may not be as you'd expect.  In particular, we don't
     * fire any keyboard or mouse events.  If you want to ensure keyboard events are fired, consider
     * using something like [.sendKeys] with the backspace key.  To ensure
     * you get a change event, consider following with a call to [.sendKeys]
     * with the tab key.
     */
    override fun clear() = element.clear()

    /**
     * If this current element is a form, or an element within a form, then this will be submitted to
     * the remote server. If this causes the current page to change, then this method will block until
     * the new page is loaded.
     *
     * @throws NoSuchElementException If the given element is not within a form
     */
    override fun submit() = element.submit()

    /**
     * Where on the page is the top left-hand corner of the rendered element?
     *
     * @return A point, containing the location of the top left-hand corner of the element
     */
    override fun getLocation(): Point = element.location

    /**
     * Capture the screenshot and store it in the specified location.
     *
     *
     * For WebDriver extending TakesScreenshot, this makes a best effort
     * depending on the browser to return the following in order of preference:
     *
     *  * Entire page
     *  * Current window
     *  * Visible portion of the current frame
     *  * The screenshot of the entire display containing the browser
     *
     *
     *
     * For WebElement extending TakesScreenshot, this makes a best effort
     * depending on the browser to return the following in order of preference:
     * - The entire content of the HTML element
     * - The visible portion of the HTML element
     *
     * @param <X> Return type for getScreenshotAs.
     * @param target target type, @see OutputType
     * @return Object in which is stored information about the screenshot.
     * @throws WebDriverException on failure.
     * @throws UnsupportedOperationException if the underlying implementation does not support
     * screenshot capturing.
    </X> */
    override fun <X : Any?> getScreenshotAs(target: OutputType<X>?) = element.getScreenshotAs(target)

    /**
     * Find the first [WebElement] using the given method. See the note in
     * [.findElements] about finding via XPath.
     * This method is affected by the 'implicit wait' times in force at the time of execution.
     * The findElement(..) invocation will return a matching row, or try again repeatedly until
     * the configured timeout is reached.
     *
     * findElement should not be used to look for non-present elements, use [.findElements]
     * and assert zero length response instead.
     *
     * @param by The locating mechanism
     * @return The first matching element on the current context.
     * @throws NoSuchElementException If no matching elements are found
     * @see org.openqa.selenium.By
     *
     * @see org.openqa.selenium.WebDriver.Timeouts
     */
    override fun <T : WebElement?> findElement(by: By?): T = element.findElement<T>(by)

    /**
     * Click this element. If this causes a new page to load, you
     * should discard all references to this element and any further
     * operations performed on this element will throw a
     * StaleElementReferenceException.
     *
     * Note that if click() is done by sending a native event (which is
     * the default on most browsers/platforms) then the method will
     * _not_ wait for the next page to load and the caller should verify
     * that themselves.
     *
     * There are some preconditions for an element to be clicked. The
     * element must be visible and it must have a height and width
     * greater then 0.
     *
     * @throws StaleElementReferenceException If the element no
     * longer exists as initially defined
     */
    override fun click() = element.click()

    /**
     * Get the tag name of this element. **Not** the value of the name attribute: will return
     * `"input"` for the element `<input name="foo" />`.
     *
     * @return The tag name of this element.
     */
    override fun getTagName() = element.tagName

    /**
     * What is the width and height of the rendered element?
     *
     * @return The size of the element on the page.
     */
    override fun getSize(): Dimension = element.size

    /**
     * Get the visible (i.e. not hidden by CSS) innerText of this element, including sub-elements,
     * without any leading or trailing whitespace.
     *
     * @return The innerText of this element.
     */
    override fun getText() = element.text

    /**
     * Determine whether or not this element is selected or not. This operation only applies to input
     * elements such as checkboxes, options in a select and radio buttons.
     *
     * @return True if the element is currently selected or checked, false otherwise.
     */
    override fun isSelected() = element.isSelected

    /**
     * Is the element currently enabled or not? This will generally return true for everything but
     * disabled input elements.
     *
     * @return True if the element is enabled, false otherwise.
     */
    override fun isEnabled() = element.isEnabled

    /**
     * Use this method to simulate typing into an element, which may set its value.
     *
     * @param keysToSend character sequence to send to the element
     */
    override fun sendKeys(vararg keysToSend: CharSequence?) = element.sendKeys(*keysToSend)

    /**
     * Get the value of the given attribute of the element. Will return the current value, even if
     * this has been modified after the page has been loaded.
     *
     *
     * More exactly, this method will return the value of the property with the given name, if it
     * exists. If it does not, then the value of the attribute with the given name is returned. If
     * neither exists, null is returned.
     *
     *
     * The "style" attribute is converted as best can be to a text representation with a trailing
     * semi-colon.
     *
     *
     * The following are deemed to be "boolean" attributes, and will return either "true" or null:
     *
     *
     * async, autofocus, autoplay, checked, compact, complete, controls, declare, defaultchecked,
     * defaultselected, defer, disabled, draggable, ended, formnovalidate, hidden, indeterminate,
     * iscontenteditable, ismap, itemscope, loop, multiple, muted, nohref, noresize, noshade,
     * novalidate, nowrap, open, paused, pubdate, readonly, required, reversed, scoped, seamless,
     * seeking, selected, truespeed, willvalidate
     *
     *
     * Finally, the following commonly mis-capitalized attribute/property names are evaluated as
     * expected:
     *
     *
     *  * If the given name is "class", the "className" property is returned.
     *  * If the given name is "readonly", the "readOnly" property is returned.
     *
     *
     * *Note:* The reason for this behavior is that users frequently confuse attributes and
     * properties. If you need to do something more precise, e.g., refer to an attribute even when a
     * property of the same name exists, then you should evaluate Javascript to obtain the result
     * you desire.
     *
     * @param name The name of the attribute.
     * @return The attribute/property's current value or null if the value is not set.
     */
    override fun getAttribute(name: String?) = element.getAttribute(name)

    /**
     * @return The location and size of the rendered element
     */
    override fun getRect(): Rectangle = element.rect

    /**
     * Get the value of a given CSS property.
     * Color values should be returned as rgba strings, so,
     * for example if the "background-color" property is set as "green" in the
     * HTML source, the returned value will be "rgba(0, 255, 0, 1)".
     *
     * Note that shorthand CSS properties (e.g. background, font, border, border-top, margin,
     * margin-top, padding, padding-top, list-style, outline, pause, cue) are not returned,
     * in accordance with the
     * [DOM CSS2 specification](http://www.w3.org/TR/DOM-Level-2-Style/css.html#CSS-CSSStyleDeclaration)
     * - you should directly access the longhand properties (e.g. background-color) to access the
     * desired values.
     *
     * @param propertyName the css property name of the element
     * @return The current, computed value of the property.
     */
    override fun getCssValue(propertyName: String?) = element.getCssValue(propertyName)

    /**
     * Find all elements within the current context using the given mechanism. When using xpath be
     * aware that webdriver follows standard conventions: a search prefixed with "//" will search the
     * entire document, not just the children of this current node. Use ".//" to limit your search to
     * the children of this WebElement.
     * This method is affected by the 'implicit wait' times in force at the time of execution. When
     * implicitly waiting, this method will return as soon as there are more than 0 items in the
     * found collection, or will return an empty list if the timeout is reached.
     *
     * @param by The locating mechanism to use
     * @return A list of all [WebElement]s, or an empty list if nothing matches.
     * @see org.openqa.selenium.By
     *
     * @see org.openqa.selenium.WebDriver.Timeouts
     */
    override fun <T : WebElement?> findElements(by: By?) = element.findElements<T>(by)

}

