package com.wahidabd.siketan.utils.components

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.LayoutFormAuthBinding


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class FormAuthentication @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: LayoutFormAuthBinding

    val editText by lazy { binding.edt}
    private var isHide = true

    private var hint = emptyString()
    private var iconLabel = 0
    private var isPassword = false
    private var type: MyInputType = MyInputType.TEXT

    init {
        setupAttributes(attrs)
        setupView()
    }

    private fun setupView(){
        binding = LayoutFormAuthBinding.inflate(context.layoutInflater, this)

        with(binding){
            edt.hint = hint
            edt.inputType = type.value
            imgIcon.setImageResource(iconLabel)

            if (isPassword) {
                edt.setCompoundDrawables(null, null, ContextCompat.getDrawable(context, R.drawable.ic_google), null)
                edt.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FormAuthentication, 0, 0)
        hint = attributes.getString(R.styleable.FormAuthentication_hintLabel).orEmpty()
        iconLabel = attributes.getResourceId(R.styleable.FormAuthentication_iconLabel, 0)
        isPassword = attributes.getBoolean(R.styleable.FormAuthentication_is_password, false)
        type = attributes.getInteger(R.styleable.FormAuthentication_input_type, 0).let {
            MyInputType.values()[it]
        }
        attributes.recycle()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setOnDrawableClick(action: () -> Unit) = with(binding){
        if (isPassword){
            edt.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP){
                    if (motionEvent.rawX <= edt.totalPaddingRight){
                        action.invoke()

                        return@setOnTouchListener true
                    }
                }

                return@setOnTouchListener false
            }
        }
    }

    enum class MyInputType(val value: Int) {
        TEXT(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL ),
        EMAIL(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS),
        PASSWORD(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD),
        NUMBER(InputType.TYPE_CLASS_PHONE),
    }

}