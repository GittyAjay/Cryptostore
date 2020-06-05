package com.example.cryptostore.modules

data class module(val username: String, val password: String) {
    constructor() : this("", "")
}