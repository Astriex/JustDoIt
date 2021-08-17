package com.astriex.justdoit.util

import androidx.appcompat.widget.SearchView


// inline added to make the code more efficient
// crossinline required to be able to use the listener fun in the onQueryTextChange fun
inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener.invoke(newText.orEmpty())
            return true
        }

    })
}