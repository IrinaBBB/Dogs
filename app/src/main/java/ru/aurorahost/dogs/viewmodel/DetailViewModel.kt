package ru.aurorahost.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aurorahost.dogs.model.DogBreed

class DetailViewModel : ViewModel() {
    val dog = MutableLiveData<DogBreed>()
    val dogsError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun loadDog() {
        val dog1 = DogBreed("1", "Corgi", "10 years", "", "", "")

        dog.value = dog1
        dogsError.value = false
        loading.value = false
    }
}