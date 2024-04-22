package by.zm.quizlet.core.ui.ext

import android.view.View

fun Boolean.toVisibility(
    invisibleMode: Int = View.GONE
): Int = if (this) View.VISIBLE else invisibleMode