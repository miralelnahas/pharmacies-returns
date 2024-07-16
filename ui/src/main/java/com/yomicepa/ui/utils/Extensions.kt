package com.yomicepa.ui.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <E> Fragment.observe(events: Flow<E>, actions: (it: E) -> Unit): Job {
    return lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
            events.collect {
                actions.invoke(it)
            }
        }
    }
}
