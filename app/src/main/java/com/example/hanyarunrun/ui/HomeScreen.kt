package com.example.hanyarunrun.ui

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hanyarunrun.viewmodel.DataViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.layout.ContentScale
import com.example.hanyarunrun.R


@Composable
fun HomeScreen(navController: NavHostController, viewModel: DataViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.lock),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Selamat Datang di Aplikasi Data Pengangguran", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("list") }, // Navigasi ke DataListScreen
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Lihat Data")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("form") }, // Navigasi ke DataEntryScreen
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Tambah Data")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("pengangguran") }, // Navigasi ke PengangguranScreen
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Data Pengangguran")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("profile") }, // Navigasi ke ProfileScreen
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Profile")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController, viewModel = DataViewModel(Application()))
}

