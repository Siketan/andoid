package id.go.ngawikab.siketan.utils.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.LinearLayout
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.disable
import com.wahidabd.library.utils.exts.enable
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.toStringTrim
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.LayoutFormInputBinding


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
    val til by lazy { binding.til }
    val disable by lazy { binding.til.disable() }

    private var hint = emptyString()
    private var type: FormType = FormType.TEXT
    private var isActive: Boolean = true

    init {
        setupAttributes(attrs)
        setupView()
    }

    private fun setupView() {
        binding = LayoutFormInputBinding.inflate(context.layoutInflater, this)

        binding.til.hint = hint
        binding.edt.inputType = type.typeInfo
        if(!isActive) binding.til.disable() else binding.til.enable()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FormInput, 0, 0)
        hint = attributes.getString(R.styleable.FormInput_labelHint).orEmpty()
        isActive = attributes.getBoolean(R.styleable.FormInput_isActive, true)
        type = attributes.getInteger(R.styleable.FormInput_formInputType, 0).let {
            FormType.values()[it]
        }
        attributes.recycle()
    }

    fun setText(text: String) = with(binding){
        edt.setText(text)
    }

    fun getText(): String = with(binding){
        return edt.text.toString()
    }

    enum class FormType(val typeInfo: Int) {
        TEXT(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL),
        TEXT_MULTILINE(InputType.TYPE_TEXT_FLAG_MULTI_LINE),
        NUMBER(InputType.TYPE_CLASS_NUMBER)
    }

}