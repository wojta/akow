package org.aerogear.akow.exceptions

import org.aerogear.akow.dsl.base.Application
import org.aerogear.akow.pageobject.PageObject


/**
 * Exception for notifying test developer about [PageObject] was not added to the [Application.screens].
 */
class NoPageObjectFound(msg: String) : Exception(msg)