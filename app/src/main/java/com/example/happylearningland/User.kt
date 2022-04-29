package com.example.happylearningland

import java.util.*

data class User(var character : String = "", var coins : Int = 0, var tasks : List<String> = emptyList(), var date : Date? = null ) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "character" to character,
            "coins" to coins,
            "tasks" to tasks
        )
    }
}

