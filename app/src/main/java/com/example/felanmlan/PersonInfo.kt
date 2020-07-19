package com.example.felanmlan

import java.io.Serializable

class PersonInfo(
    var id: String? = null,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: String = "",
    var personnr: String = "",
    var typ: String = "",
    var location: String = "",
    var selected: String = "",
    var image: String = "",
    var serviceDone: Boolean = false
) : Serializable {}

//Test branch


