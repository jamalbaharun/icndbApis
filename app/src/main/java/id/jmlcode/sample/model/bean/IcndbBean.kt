package id.jmlcode.sample.model.bean

import com.google.gson.annotations.SerializedName

data class IcndbBean(
        @SerializedName("type")
        val type: String = "",
        @SerializedName("value")
        val value: List<Joke> = listOf()
)

data class Joke(
        @SerializedName("categories")
        val categories: List<Any> = listOf(),
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("joke")
        val joke: String = ""
)