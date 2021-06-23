package ir.nwise.app.ui.utils

import android.view.View
import android.widget.Toast
import ir.nwise.app.R

/**
 * Replace old items with new items
 * @param items New items
 */
fun <T> MutableCollection<T>.replaceAll(items: Collection<T>) {
    clear()
    addAll(items)
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.toastNoInternetConnection() {
    Toast.makeText(
        context,
        resources?.getString(R.string.no_internet_connection),
        Toast.LENGTH_SHORT
    ).show()
}

fun View.toastOopsError() {
    Toast.makeText(
        context,
        resources?.getString(R.string.oops),
        Toast.LENGTH_SHORT
    ).show()
}