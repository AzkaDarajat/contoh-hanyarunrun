package com.example.hanyarunrun.data

import retrofit2.http.GET
import retrofit2.http.Query

interface DataJabarApiService {
    @GET("od_21012_tingkat_pengangguran_terbuka__semester_prov_di_ind")
    suspend fun getDataJabar(@Query("page") page: Int): PengangguranResponse
}