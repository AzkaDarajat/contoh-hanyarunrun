package com.example.hanyarunrun.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hanyarunrun.R
import com.example.hanyarunrun.viewmodel.DataViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: DataViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Aplikasi Data Pengangguran di Indonesia",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(100.dp)) // Memberi jarak antara judul dan konten

        Image(
            painter = painterResource(id = R.drawable.lock), // Ganti dengan nama gambar Anda
            contentDescription = "Foto Profil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Data Profil Hardcoded
        Text(text = "Nama: Azka Darajat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "NIM: 231511036", fontSize = 18.sp)
        Text(text = "Email: azka@gmail.com", fontSize = 18.sp)
    }
}
