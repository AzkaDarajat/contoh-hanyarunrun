package com.example.hanyarunrun.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hanyarunrun.ui.AppNavHost
import com.example.hanyarunrun.ui.theme.HanyarunrunTheme
import com.example.hanyarunrun.viewmodel.DataViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HanyarunrunTheme {
                // Inisialisasi ViewModel
                val dataViewModel: DataViewModel = viewModel()
                // Menampilkan Navigation Host
                AppNavHost(viewModel = dataViewModel)
            }
        }
    }
}


//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController) }
//    ) { padding ->
//        NavHost(
//            navController = navController,
//            startDestination = "pengangguran"
//        ) {
//            composable("pengangguran") { PengangguranScreen(navController) }
//            composable("profile") { ProfileScreen(navController) }
//        }
//    }
//}

