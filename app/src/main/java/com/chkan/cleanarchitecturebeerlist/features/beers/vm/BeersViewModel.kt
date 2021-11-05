package com.chkan.cleanarchitecturebeerlist.features.beers.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chkan.cleanarchitecturebeerlist.core.datatype.ResultType
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.model.BeersEntity
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.usecase.GetBeersUseCase
import com.chkan.cleanarchitecturebeerlist.features.beers.vm.model.BeerUI
import com.chkan.cleanarchitecturebeerlist.core.datatype.Result
import com.chkan.cleanarchitecturebeerlist.features.beers.vm.mapper.BeersEntityToUIMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BeersViewModel(
        private val getBeersUseCase: GetBeersUseCase
) : ViewModel() {

    private val beersMutableLiveData: MutableLiveData<List<BeerUI>> = MutableLiveData()
    val beersLiveData: LiveData<List<BeerUI>>
        get() = beersMutableLiveData

    private val areEmptyBeersMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val areEmptyBeersLiveData: LiveData<Boolean>
        get() = areEmptyBeersMutableLiveData

    private val isErrorMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = isErrorMutableLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean>
        get() = isLoadingMutableLiveData

    init {
        handleBeersLoad()
    }

    fun handleBeersLoad() {
        isLoadingLiveData(true)
        viewModelScope.launch {
            val beersEntityResult: Result<BeersEntity> = getBeersUseCase.execute()

            updateAppropriateLiveData(beersEntityResult)
        }
    }

    private fun updateAppropriateLiveData(result: Result<BeersEntity>) {
        if (isResultSuccess(result.resultType)) {
            onResultSuccess(result.data)
        } else {
            onResultError()
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultSuccess(beersEntity: BeersEntity?) {
        val beers = BeersEntityToUIMapper.map(beersEntity?.beers)

        if (beers.isEmpty()) {
            areEmptyBeersMutableLiveData.value = true
        } else {
            beersMutableLiveData.value = beers
        }

        isLoadingLiveData(false)
    }

    /**
     *  The delay is to avoid the screen flash between the transition from AlertDialog to ProgressBar
     * */
    private fun onResultError() {
        viewModelScope.launch {
            delay(300)
            isLoadingLiveData(false)
        }.invokeOnCompletion {
            isErrorMutableLiveData.value = true
        }
    }

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingMutableLiveData.value = isLoading
    }

}
