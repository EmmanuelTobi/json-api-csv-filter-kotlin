package com.cosmic.venten.listeners

import com.cosmic.venten.model.api_model

interface OnAPIDataGotten {

    fun api_json_loaded(apiModelArr: Array<api_model>)
}