package com.jozze.transwift

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform