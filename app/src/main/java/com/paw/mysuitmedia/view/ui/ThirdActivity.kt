package com.paw.mysuitmedia.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.paw.mysuitmedia.databinding.ActivityThirdBinding
import com.paw.mysuitmedia.model.remote.response.DataItem
import com.paw.mysuitmedia.view.adapter.UserAdapter
import com.paw.mysuitmedia.view.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupToolbar()
        setupSwipeRefresh()

        lifecycleScope.launch {
            userViewModel.users.collectLatest { pagingData ->
                userAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(object : UserAdapter.OnItemClickListener {
            override fun onItemClick(user: DataItem) {
                val intent = Intent().apply {
                    putExtra("selectedUserName", "${user.firstName} ${user.lastName}")
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = userAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.refresh.setOnRefreshListener {
            userAdapter.refresh()
            binding.refresh.isRefreshing = false
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarThird)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}