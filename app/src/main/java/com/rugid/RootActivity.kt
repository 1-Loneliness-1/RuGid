package com.rugid

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.rugid.core.ui.navigation.AppNavigator
import com.rugid.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity(), AppNavigator {

    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvRootContainer) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.networkErrorFragment -> {
                    binding.bnvMainBottomMenu.visibility = View.GONE
                    binding.llMainToolbar.visibility = View.GONE
                }

                else -> {
                    binding.bnvMainBottomMenu.visibility = View.VISIBLE
                    binding.llMainToolbar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun openNetworkErrorFragment() {
        navController.navigate(R.id.networkErrorFragment)
    }

    override fun goBack() {
        navController.popBackStack()
    }

}