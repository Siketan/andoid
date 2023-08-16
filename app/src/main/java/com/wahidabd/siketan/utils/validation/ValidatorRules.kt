package com.wahidabd.siketan.utils.validation

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.wahidabd.library.utils.exts.isNotNull
import com.wahidabd.library.utils.exts.toStringTrim


/**
 * Created by Wahid on 7/1/2023.
 * Github github.com/wahidabd.
 */


class NotEmptyValidator(private val edt: EditText, private val message: String): IValidator {
    override fun isValid(): Boolean {
        return edt.toStringTrim().isNotEmpty()
    }

    override fun message(): String {
        return message
    }
}

class NotEmptyTilValidator(private val edt: TextInputLayout, private val message: String): IValidator {
    override fun isValid(): Boolean {
        return edt.toStringTrim().isNotEmpty()
    }

    override fun message(): String {
        return message
    }
}

class NotEmptyText(private val text: String? = null, private val message: String): IValidator {
    override fun isValid(): Boolean {
        return !text.isNullOrEmpty()
    }

    override fun message(): String {
        return message
    }

}