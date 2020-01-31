package com.solulab.example.view.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.solulab.example.BaseActivity
import com.solulab.example.R
import com.solulab.example.databinding.ActivityLoginBinding
import com.solulab.example.view.MainActivity
import java.util.*

class LoginActivity : BaseActivity() {

    lateinit var device_id: String
    private val viewModel by lazy { LoginViewModel() }
    lateinit var binding: ActivityLoginBinding

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this
        if (savedInstanceState == null) viewModel.init(applicationContext)



        viewModel.getUser()!!.observe(this, androidx.lifecycle.Observer { loginUser ->
            if (TextUtils.isEmpty(Objects.requireNonNull(loginUser)?.email)) {
                binding.tiEmail.error = "Enter an E-Mail Address"
                binding.etEmail.requestFocus()
            } else if (!loginUser!!.isEmailValid()) {
                binding.tiEmail.error = "Enter a Valid E-mail Address"
                binding.etEmail.requestFocus()
            } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).password)) {
                binding.tiPassword.error = "Enter a Password"
                binding.etPassword.requestFocus()
            } else if (!loginUser.isPasswordLengthGreaterThan5()) {
                binding.tiPassword.error = "Enter at least 6 Digit password"
                binding.etPassword.requestFocus()
            } else {
                viewModel.getLogin()

            }
        })

        viewModel.success.observe(this, androidx.lifecycle.Observer {

            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()

        })

    }

    private fun validateFields() {

    }

}

