package com.wahidabd.siketan.utils


/**
 * Created by Wahid on 6/26/2023.
 * Github github.com/wahidabd.
 */


enum class UserRole(val role: String){
    PETANI("petani"),
    PENYULUH("penyuluh")
}

enum class ChartTypeQuery(val type: String){
    TAHUNAN("Tahunan"),
    BULANAN("Bulanan")
}

enum class ChartKomuditasQuery(val type: String){
    FRUIT("Buah-Buahan"),
    VEGETABLE("Sayuran")
}