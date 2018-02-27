package org.aerogear.akow.exceptions

/**
 * DSL section or command was added more than once where it's required to have only one.
 */
class CommandDuplicationException(msg: String) : Exception(msg)