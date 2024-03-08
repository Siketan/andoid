package id.go.ngawikab.siketan.domain.auth.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

data class RegisterRequest(
    @SerializedName("NIK")
    val NIK: String,
    @SerializedName("NoWa")
    val no_wa: String,
    val nama: String,
    val password: String,
    val alamat:String,
    val desa:String,
    val kecamatan:String,
    val gapoktan:String,
    val penyuluh:Int,
    val namaKelompok:String
)

@Parcelize
data class RegisterBaseData(
    val nik: String,
    val no_wa: String,
    val nama: String,
    val password: String,
):Parcelable