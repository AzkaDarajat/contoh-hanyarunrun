package com.example.hanyarunrun.data

import com.google.gson.annotations.SerializedName

data class PengangguranResponse (
    @SerializedName("data") val data: List<PengangguranEntity>
)

data class PengangguranEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("kode_provinsi") val kodeProvinsi: Double,
    @SerializedName("nama_provinsi") val namaProvinsi: String,
    @SerializedName("kode_bulan") val kodeBulan: Int,
    @SerializedName("nama_bulan") val namaBulan: String,
    @SerializedName("tingkat_pengangguran_terbuka") val tingkatPengangguranTerbuka: Double,
    @SerializedName("satuan") val satuan: String,
    @SerializedName("tahun") val tahun: Int
)
