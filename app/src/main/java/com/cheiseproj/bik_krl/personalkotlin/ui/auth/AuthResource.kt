package com.cheiseproj.bik_krl.personalkotlin.ui.auth

enum class AuthStatus{
    LOADING,AUTHENTICATED,ERROR,LOGOUT
}

data class AuthResource <out T>(val status: AuthStatus, val data:T?, val message:String?){
    companion object{
        fun <T> authenticate(data:T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.AUTHENTICATED,
                data,
                null
            )
        }
        fun <T> loading(data:T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.LOADING,
                data,
                null
            )
        }

        fun <T> error(msg:String,data:T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.ERROR,
                data,
                msg
            )
        }

        fun <T> logout(): AuthResource<T> {
            return AuthResource(
                AuthStatus.LOGOUT,
                null,
                null
            )
        }
    }
}