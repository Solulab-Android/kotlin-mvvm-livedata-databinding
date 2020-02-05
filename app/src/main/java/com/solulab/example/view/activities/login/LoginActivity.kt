package com.solulab.example.view.activities.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.solulab.example.R
import com.solulab.example.databinding.ActivityLoginBinding
import com.solulab.example.view.activities.BaseActivity
import com.solulab.example.view.activities.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : BaseActivity(), TextWatcher {

    lateinit var device_id: String
    private val viewModel by lazy { LoginViewModel(this) }
    lateinit var binding: ActivityLoginBinding

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this
        binding.etEmail.requestFocus()

        etEmail.addTextChangedListener(this)
        etPassword.addTextChangedListener(this)

        viewModel.getUser().observe(this, androidx.lifecycle.Observer { loginUser ->
            if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).email)) {
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

        viewModel.loginMutableLiveData.observe(this, androidx.lifecycle.Observer {

            if (it != null && it.success.equals("true")) {
                val intent = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(intent)
                Toast.makeText(this, it.success, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, it.success, Toast.LENGTH_SHORT).show()

            }
        })

    }


    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        tiEmail.isErrorEnabled = false
        tiPassword.isErrorEnabled = false
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}

