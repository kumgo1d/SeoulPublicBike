package com.goldcompany.apps.koreabike.seoul

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goldcompany.apps.koreabike.seoul.seoul_api.SeoulRepository
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike

class SeoulMapViewModel : ViewModel() {
    var bikes1 = MutableLiveData<SeoulBike>()
    var bikes2 = MutableLiveData<SeoulBike>()
    var bikes3 = MutableLiveData<SeoulBike>()

    fun getBikes1(): LiveData<SeoulBike> {
        bikes1 = SeoulRepository.getBikeData1()
        return bikes1
    }

    fun getBikes2(): LiveData<SeoulBike> {
        bikes2 = SeoulRepository.getBikeData2()
        return bikes2
    }

    fun getBikes3(): LiveData<SeoulBike> {
        bikes3 = SeoulRepository.getBikeData3()
        return bikes3
    }
}

class SeoulMapViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeoulMapViewModel() as T
    }
}