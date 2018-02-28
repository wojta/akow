package org.aerogear.akow.exceptions

import org.aerogear.akow.pageobject.PageObject


/**
 * Exception for notifying test developer about specific [PageObject] being implemented by multiple screens of the same platform.
 */
class MultiplePageObjectsFound(msg: String) : Exception(msg)