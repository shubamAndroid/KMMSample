package org.project.kmp.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform