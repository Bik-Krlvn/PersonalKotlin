package com.cheiseproj.bik_krl.personalkotlin.utils.provider

object CapSentencesProvider{
    fun setValue(data:String?):String{
        return data?.split(' ')!!.joinToString(" ") {it.capitalize()  }
    }
}