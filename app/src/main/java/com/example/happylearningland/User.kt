package com.example.happylearningland

data class User(var character : String = "", var coins : Int = 0, var tasks : List<String> = emptyList()) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "character" to character,
            "coins" to coins,
            "tasks" to tasks
        )
    }
}

