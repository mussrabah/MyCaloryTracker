package com.musscoding.core.domain.use_case

class FilterOutDigits {

    //This is a use case, which is just a class that contains at least a single
    //exposed function that other classes can call
    //and that function just does one thing and contains business logic
    //and this class will be provided using dagger hilt in the app module

    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}