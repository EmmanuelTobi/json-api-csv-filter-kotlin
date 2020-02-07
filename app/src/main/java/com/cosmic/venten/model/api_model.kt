package com.cosmic.venten.model

import java.io.Serializable

class api_model : Serializable {

    var id: Int? = null
    var start_year: Int? = null
    var end_year: Int? = null
    var gender: String? = null
    var countries: ArrayList<String>? = null
    var colors: ArrayList<String>? = null
}