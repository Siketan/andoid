package com.wahidabd.siketan.utils.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.LayoutFormInputBinding


/**
 * Created by Wahid on 6/14/2023.
 * Github github.com/wahidabd.
 */


class FormInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: LayoutFormInputBinding
    val edittext by lazy { binding.til.toStringTrim() }

    private var hint = emptyString()

    init {
        setupAttributes(attrs)
        setupView()
    }

    private fun setupView() {
        binding = LayoutFormInputBinding.inflate(context.layoutInflater, this)

        binding.til.hint = hint

    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FormInput, 0, 0)
        hint = attributes.getString(R.styleable.FormInput_labelHint).orEmpty()
        attributes.recycle()
    }

}