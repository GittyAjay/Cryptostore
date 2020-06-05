package com.example.cryptostore.modules

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Save(
    var username: String? = "",
    var password: String? = ""

)
