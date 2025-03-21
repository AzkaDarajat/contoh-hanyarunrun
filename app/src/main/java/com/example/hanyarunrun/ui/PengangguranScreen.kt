package com.example.hanyarunrun.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.hanyarunrun.viewmodel.DataViewModel
import com.example.hanyarunrun.data.PengangguranEntity
import com.example.hanyarunrun.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengangguranScreen(navController: NavController, viewModel: DataViewModel = viewModel()) {
    val dataPengangguran by viewModel.dataPengangguran.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState(initial = false)

    var searchQuery by remember { mutableStateOf("") }
    var selectedProvinsi by remember { mutableStateOf("Provinsi") }
    val provinsiList = listOf("Provinsi") + dataPengangguran.map { it.namaProvinsi }.distinct()

    var selectedYear by remember { mutableStateOf("Tahun") }
    val tahunList = listOf("Tahun") + dataPengangguran.map { it.tahun.toString() }.distinct().sortedDescending()

    var selectedScreen by remember { mutableStateOf("Lihat Data") }

    LaunchedEffect(Unit) {
        viewModel.fetchPengangguran()
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Tingkat Pengangguran Indonesia") })
                if (selectedScreen == "Lihat Data") {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Cari Provinsi") },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DropdownMenuComponent(provinsiList, selectedProvinsi, Modifier.weight(1f)) {
                            selectedProvinsi = it
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        DropdownMenuComponent(tahunList, selectedYear, Modifier.weight(1f)) {
                            selectedYear = it
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(selectedScreen) { selectedScreen = it }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    when (selectedScreen) {
                        "Lihat Data" -> DataList(dataPengangguran, selectedProvinsi, selectedYear, searchQuery)
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(selectedScreen: String, onScreenSelected: (String) -> Unit) {
    val items = listOf(
        "Lihat Data" to Icons.Default.Home,
    )

    NavigationBar {
        items.forEach { (screen, icon) ->
            NavigationBarItem(
                selected = selectedScreen == screen,
                onClick = { onScreenSelected(screen) },
                label = { Text(screen) },
                icon = { Icon(icon, contentDescription = screen) }
            )
        }
    }
}


@Composable
fun DataList(dataPengangguran: List<PengangguranEntity>, selectedProvinsi: String, selectedYear: String, searchQuery: String) {
    val filteredData = dataPengangguran.filter {
        (selectedProvinsi == "Provinsi" || it.namaProvinsi == selectedProvinsi) &&
                (selectedYear == "Tahun" || it.tahun.toString() == selectedYear) &&
                (searchQuery.isBlank() || it.namaProvinsi.contains(searchQuery, ignoreCase = true))
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(filteredData) { item ->
            PengangguranItem(item)
        }
    }
}

@Composable
fun DropdownMenuComponent(list: List<String>, selectedValue: String, modifier: Modifier, onSelectionChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.padding(4.dp)) {
        Button(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(selectedValue)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            list.forEach { value ->
                DropdownMenuItem(
                    text = { Text(value) },
                    onClick = {
                        onSelectionChange(value)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun PengangguranItem(item: PengangguranEntity) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Provinsi: ${item.namaProvinsi}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Bulan: ${item.namaBulan} ${item.tahun}")
            Text(text = "Tingkat Pengangguran: ${item.tingkatPengangguranTerbuka} ${item.satuan}")
        }
    }
}

@Composable
fun BarChartScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Tampilan Bar Chart (Implementasi selanjutnya)")
    }
}

//@Composable
//fun ProfileScreen(navController: NavHostController, viewModel: DataViewModel) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.lock),
//                contentDescription = "Foto Profil",
//                modifier = Modifier
//                    .size(150.dp) // Ukuran gambar
//                    .clip(CircleShape) // Bentuk lingkaran
//                    .border(2.dp, Color.Gray, CircleShape) // Tambahkan border
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "Azka Darajat - 231511036",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}

