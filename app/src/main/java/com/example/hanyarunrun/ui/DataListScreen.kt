package com.example.hanyarunrun.ui


import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.hanyarunrun.viewmodel.DataViewModel
import com.example.hanyarunrun.data.DataEntity
import com.example.hanyarunrun.ui.theme.*
import com.example.hanyarunrun.ui.theme.SecondaryColor
import androidx.compose.runtime.LaunchedEffect




@Composable
fun DataListScreen(navController: NavHostController, viewModel: DataViewModel) {
    val dataList by viewModel.dataList.observeAsState(emptyList())
    val context = LocalContext.current


    var tampilKonfir by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<DataEntity?>(null) }
    if (dataList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No Data Available", style = MaterialTheme.typography.headlineMedium)
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dataList) { item ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Provinsi: ${item.namaProvinsi} (${item.kodeProvinsi})",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Kabupaten/Kota: ${item.namaKabupatenKota} (${item.kodeKabupatenKota})",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Total: ${item.total} ${item.satuan}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Tahun: ${item.tahun}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("edit/${item.id}")
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor)
                                ) {
                                    Icon(imageVector = Icons.Default.Edit, contentDescription = "tombol edit")
                                    Spacer(modifier = Modifier.width(1.dp))
                                    Text(text = "Edit")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        selectedItem = item
                                        tampilKonfir = true
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = merah)
                                ) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription =  "tombol delete")
                                    Spacer(modifier = Modifier.width(1.dp))
                                    Text(text = "Delete")
                                }
                            }
                        }
                    }
                }
            }
            FloatingActionButton(
                onClick = { navController.navigate("form") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = SecondaryColor
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Data")
            }
        }
    }
    if (tampilKonfir) {
        AlertDialog(
            onDismissRequest = { tampilKonfir = false },
            title = { Text(text = "Konfirmasi Hapus") },
            text = { Text(text = "Apakah anda yakin ingin menghapus data ini?") },
            confirmButton = {
                Button(
                    onClick = {
                        selectedItem?.let {
                            viewModel.deleteData(it)
                            Toast.makeText(context, "Data Berhasil dihapus!", Toast.LENGTH_SHORT).show()
                        }
                        tampilKonfir = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = merah)
                ) {
                    Text("Hapus")
                }
            },
            dismissButton = {
                Button(
                    onClick = { tampilKonfir = false },
                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor)
                ) {
                    Text("Batal")
                }
            }
        )
    }
}
