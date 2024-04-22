package by.zm.quizlet.core.ui.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import by.zm.quizlet.core.ui.R
import by.zm.quizlet.core.ui.databinding.PartToolbarBinding
import by.zm.quizlet.core.ui.ext.toVisibility

class ToolbarView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var _binding: PartToolbarBinding? = null
    val binding get() = _binding ?: error("toolbar binding error")

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context
    ) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_toolbar, this, true)
        _binding = PartToolbarBinding.bind(this)
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.ToolbarView, defStyleAttr, defStyleRes
        ).apply {
            try {
                initAttrs(this)
            } finally {
                recycle()
            }
        }
    }

    private fun initAttrs(typed: TypedArray) = with(binding) {
        textViewTitle.text = typed.getString(R.styleable.ToolbarView_title)
        imageViewBack.visibility = typed
            .getBoolean(R.styleable.ToolbarView_isBack, true).toVisibility()
        val extraImage = typed.getResourceId(R.styleable.ToolbarView_extraImage, -1)
        if (extraImage != -1) {
            imageViewExtra.setImageResource(extraImage)
            imageViewExtra.isVisible = true
        }
    }
}