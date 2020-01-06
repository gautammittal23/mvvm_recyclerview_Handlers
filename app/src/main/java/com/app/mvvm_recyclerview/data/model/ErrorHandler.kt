package com.app.mvvm_recyclerview.data.model

class ErrorHandler(ErrorMessage: String, ErrorField: Int) {

    val errorMessage: String
    val errorField: Int
    init {
        errorMessage = ErrorMessage
        errorField = ErrorField
    }
}