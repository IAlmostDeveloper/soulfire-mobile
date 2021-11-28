package ru.ialmostdeveloper.soulfire_mobile.network

class Session {
    var login: String
    var password: String
    var token: String
    var isValid: Boolean

    constructor() {
        login = ""
        password = ""
        token = ""
        isValid = false
    }

    constructor(login: String, password: String, token: String, isValid: Boolean) {
        this.login = login
        this.password = password
        this.token = token
        this.isValid = isValid
    }
}
