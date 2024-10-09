package id.go.ngawikab.siketan.domain.farm.model.response

import com.wahidabd.library.utils.common.emptyString
import id.go.ngawikab.siketan.data.farm.model.store.ProductResponse
import id.go.ngawikab.siketan.data.farm.model.store.ProductUserAccountResponse


/**
 * Created by Wahid on 6/18/2023.
 * Github github.com/wahidabd.
 */


data class Product(
    val createdAt: String? = emptyString(),
    val dataAccount: ProductUserAccount? = null,
    val dataPersonId: String? = emptyString(),
    val deskripsi: String? = emptyString(),
    val fotoTanaman: String? = emptyString(),
    val harga: String? = emptyString(),
    val id: Int? = 0,
    val namaProducts: String? = emptyString(),
    val profesiPenjual: String? = emptyString(),
    val satuan: String? = emptyString(),
    val status: String? = emptyString(),
    val stok: Int? = 0,
    val updatedAt: String? = emptyString()
)

data class ProductUserAccount(
    val isVerified: Boolean? = true,
    val noWa: String? = emptyString(),
//    val dataPetani: DataPetaniResponse? = null,
    val accountID: String? = emptyString(),
    val createdAt: String? = emptyString(),
    val peran: String? = emptyString(),
    val nama: String? = emptyString(),
    val pekerjaan: String? = emptyString(),
    val foto: String? = emptyString(),
    val id: Int? = 0,
    val email: String? = emptyString(),
//    val dataPenyuluh: DataPenyuluhResponse? = null,
    val updatedAt: String? = emptyString()
)

fun ProductResponse.toDomain(): Product =
    Product(
        createdAt = createdAt,
        dataAccount = tblAkun?.todDomain(),
        dataPersonId = accountID,
        deskripsi = deskripsi,
        fotoTanaman = fotoTanaman,
        harga = harga,
        id = id,
        namaProducts = namaProducts,
        profesiPenjual = profesiPenjual,
        status = status,
        stok = stok,
        updatedAt = updatedAt
    )

fun ProductUserAccountResponse.todDomain(): ProductUserAccount =
    ProductUserAccount(
        isVerified,
        noWa,
        accountID,
        createdAt,
        peran,
        nama,
        pekerjaan,
        foto,
        id,
        email,
        updatedAt,
    )
