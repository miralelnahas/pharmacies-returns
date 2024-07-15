package com.yomicepa.ui.login

import androidx.fragment.app.viewModels
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseFragment
import com.yomicepa.ui.databinding.FragmentLoginBinding
import com.yomicepa.ui.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override val vm: LoginViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        if (vm.isUserLoggedIn)
            navigateToReturnRequests()
    }

    override fun setupObservers() {
        super.setupObservers()
        observe(vm.event) {
            when (it) {
                LoginEvent.LoginSuccess -> navigateToReturnRequests()
            }
        }
    }

    private fun navigateToReturnRequests() {
        navigateTo(LoginFragmentDirections.actionLoginToReturnRequests())
    }


}