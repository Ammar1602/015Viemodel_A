package com.example.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.main.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel {
    var namaUsr : String by mutableStateOf("")
        private set
    var noTlp : String by mutableStateOf("")
        private set
    var jenisKL : String by mutableStateOf("")
        private set
    var emailUSr : String by mutableStateOf("")
        private set
    var alamatUSr : String by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(nm:String,tlp:String,jk:String,eml:String,al:String){
        namaUsr=nm
        noTlp=tlp
        jenisKL=jk
        emailUSr=eml
        alamatUSr=al
    }

    fun setJenisK(pilihJK:String){
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }
    fun setStatus(pilihStatus:String){
        _uiState.update { currentState -> currentState.copy(status = pilihStatus) }
    }
}