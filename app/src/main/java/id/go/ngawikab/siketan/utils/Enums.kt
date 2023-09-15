package id.go.ngawikab.siketan.utils


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

enum class CategoryType(val type: String){
    PANGAN("Tanaman Pangan"),
    PERKEBUNAN("Tanaman Perkebunan"),
    HOLTIKULTURA("Tanaman Holtikultura")
}

enum class HarverstType(val type: String){
    MUSIMAN("Musiman"),
    TAHUNAN("Tahunan")
}

enum class PlantType(val type: String){
    FRUIT("Buah"),
    VEGENT("Sayur")
}