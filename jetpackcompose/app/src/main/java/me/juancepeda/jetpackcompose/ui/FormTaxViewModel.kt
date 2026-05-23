package me.egil.estadosjectpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FormTaxViewModel : ViewModel() {

    var inputCantidad by mutableStateOf("")
    var inputPorcentaje by mutableStateOf("")

    // Estado del switch: true = redondear el resultado
    var switchRedondear by mutableStateOf(true)

    // Propiedad calculada: devuelve el impuesto como String
    val impuesto: String
        get() {
            var cantidad = inputCantidad.toDoubleOrNull() ?: 0.0
            var porcentaje = inputPorcentaje.toDoubleOrNull() ?: 0.0

            var result = cantidad * porcentaje / 100
            var impuestoRedondeado = kotlin.math.ceil(result)

            return if (switchRedondear) {
                impuestoRedondeado.toString()
            } else {
                result.toString()
            }
        }

    fun updateCantidad(text:String){
        inputCantidad = text
    }

    fun updatePorcentaje(text:String){
        inputPorcentaje = text
    }

    fun updateRedondear(it: Boolean){
        switchRedondear = it
    }
}
