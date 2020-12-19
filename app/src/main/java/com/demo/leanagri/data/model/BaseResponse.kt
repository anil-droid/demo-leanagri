package com.demo.leanagri.data.model

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

open class BaseResponse{

     @Ignore
     @SerializedName("status_code")
     val statusCode: Int? = null
     @Ignore
     @SerializedName("status_message")
     val statusMessage: String? = null
     @Ignore
     @SerializedName("success")
     val success: Boolean? = null
 }