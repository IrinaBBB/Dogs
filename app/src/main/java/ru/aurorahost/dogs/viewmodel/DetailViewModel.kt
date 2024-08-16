package ru.aurorahost.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import ru.aurorahost.dogs.model.DogBreed
import ru.aurorahost.dogs.model.DogDatabase
import java.util.UUID

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val dog = MutableLiveData<DogBreed>()
    val dogsError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun loadDog(uuid: Int) {

        launch {
            val dogFromDb = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dog.value = dogFromDb
        }
    }
}