package com.jacquessmuts.exhaustivewhen

/**
 * This forces any passed `when` expression to be exhaustive.
 *
 * It can be used like so:
 *  ```Force exhaustive when (input) {...}```
 *
 * @see [discussion](https://youtrack.jetbrains.com/issue/KT-12380)
 */
object Force {

    /**
     * This forces any passed `when` expression to be exhaustive.
     *
     * It can be used like so:
     *  ```Force exhaustive when (input) {...}```
     *
     * @see [discussion](https://youtrack.jetbrains.com/issue/KT-12380)
     */
    inline infix fun<reified T> exhaustive(any: T?) = any
}
