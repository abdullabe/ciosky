package com.example.ciosky.model.User

import com.google.gson.annotations.SerializedName

data class RequestAddUser(
    @SerializedName("firstName" ) var firstName : String? = null,
    @SerializedName("lastName"  ) var lastName  : String? = null,
    @SerializedName("age"       ) var age       : String? = null
)
