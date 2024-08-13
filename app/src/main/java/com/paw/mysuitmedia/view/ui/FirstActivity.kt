package com.paw.mysuitmedia.view.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.paw.mysuitmedia.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupAction()
    }

    private fun setupAction() {
        binding.btnCheck.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val palindromeInput = binding.etPalindrome.text.toString().trim()

            if (name.isEmpty()) {
                binding.etName.error = "Name cannot be empty"
                return@setOnClickListener
            }

            if (palindromeInput.isEmpty()) {
                binding.etPalindrome.error = "Palindrome input cannot be empty"
                return@setOnClickListener
            }

            val isPalindrome = palindromeInput.replace(" ", "").equals(
                palindromeInput.replace(" ", "").reversed(), ignoreCase = true
            )

            val message = if (isPalindrome) "isPalindrome" else "not palindrome"

            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", binding.etName.text.toString().trim())
            startActivity(intent)
        }
    }
}

//    private fun playAnimation() {
//        val  = ObjectAnimator.ofFloat(binding.t, View.ALPHA, 1f).setDuration(100)
//        val  =
//            ObjectAnimator.ofFloat(binding.t, View.ALPHA, 1f).setDuration(100)
//        val  =
//            ObjectAnimator.ofFloat(binding.t, View.ALPHA, 1f).setDuration(100)
//        val  = ObjectAnimator.ofFloat(binding.t, View.ALPHA, 1f).setDuration(100)
//
//        AnimatorSet().apply {
//            playSequentially(
//                text,
//                email,
//                password,
//                login
//            )
//            startDelay = 100
//        }.start()
//    }
