package org.example.krona

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform