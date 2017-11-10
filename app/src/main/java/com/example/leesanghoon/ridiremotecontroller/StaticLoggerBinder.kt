package com.example.leesanghoon.ridiremotecontroller

import io.underdark.util.nslogger.NSLoggerFactory
import org.slf4j.ILoggerFactory


class StaticLoggerBinder private constructor() {
    val loggerFactory: ILoggerFactory
    val loggerFactoryClassStr = NSLoggerFactory::class.java.name

    init {
        loggerFactory = NSLoggerFactory("underdark")
    }

    companion object {
        val singleton = StaticLoggerBinder()
        var REQUESTED_API_VERSION = "1.7.12"  // !final
    }
}