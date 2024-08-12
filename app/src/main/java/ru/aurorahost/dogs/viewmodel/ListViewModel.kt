package ru.aurorahost.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aurorahost.dogs.model.DogBreed

class ListViewModel: ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreed("1", "Corgi", "10 years", "", "", "")
        val dog2 = DogBreed("1", "Bulldog", "10 years", "", "", "")
        val dog3 = DogBreed("1", "Collie", "10 years", "", "", "")
        val dogList = arrayListOf(dog1, dog2, dog3)

        dogs.value = dogList
        dogsError.value = false
        loading.value = false
    }
}
