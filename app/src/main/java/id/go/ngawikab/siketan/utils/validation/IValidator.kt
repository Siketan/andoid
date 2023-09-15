package id.go.ngawikab.siketan.utils.validation


/**
 * Created by Wahid on 7/1/2023.
 * Github github.com/wahidabd.
 */


interface IValidator {
    fun isValid(): Boolean
    fun message(): String
}