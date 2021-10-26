package com.mlhysrszn.analyticahousetestcase.data.remote

import com.mlhysrszn.analyticahousetestcase.util.BASE_URL

class ApiUtils {

    companion object {
        fun getApiService(): ApiService {
            return ApiClient.getClient(BASE_URL).create(ApiService::class.java)
        }
    }
}