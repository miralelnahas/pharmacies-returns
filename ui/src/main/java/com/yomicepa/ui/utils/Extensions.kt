package com.yomicepa.ui.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
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
