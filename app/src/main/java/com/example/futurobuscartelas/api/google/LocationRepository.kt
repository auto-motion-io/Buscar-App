package com.example.futurobuscartelas.api.google

import android.content.Context
import android.util.Log
import com.example.futurobuscartelas.location.LocationManager
import com.example.futurobuscartelas.location.LocationPermissions
import com.example.futurobuscartelas.location.UserLocation
import retrofit2.Call

class LocationRepository {
    fun fetchCoordinates(
        apiKey: String,
        address: String,
        callback: (Pair<Double, Double>?) -> Unit
    ) {
        val service = RetrofitClient.instance
        service.getCoordinates(address, apiKey)
            .enqueue(object : retrofit2.Callback<GeocodeResponse> {
                override fun onResponse(
                    call: Call<GeocodeResponse>,
                    response: retrofit2.Response<GeocodeResponse>
                ) {
                    if (response.isSuccessful) {
                        val location = response.body()?.results?.firstOrNull()?.geometry?.location
                        if (location != null) {
                            callback(Pair(location.lat, location.lng))
                        } else {
                            callback(null)
                        }
                    } else {
                        Log.e("Location", "Failed")
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<GeocodeResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback(null)
                }
            })
    }

    fun fetchDistance(
        apiKey: String,
        origins: String,
        destinations: String,
        callback: (Int?) -> Unit
    ) {
        val service = RetrofitClient.instance
        service.getDistance(origins, destinations, apiKey)
            .enqueue(object : retrofit2.Callback<DistanceMatrixResponse> {
                override fun onResponse(
                    call: Call<DistanceMatrixResponse>,
                    response: retrofit2.Response<DistanceMatrixResponse>
                ) {
                    if (response.isSuccessful) {
                        val distance =
                            response.body()?.rows?.firstOrNull()?.elements?.firstOrNull()?.distance?.value
                        callback(distance)
                    } else {
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<DistanceMatrixResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback(null)
                }
            })
    }

    fun getLocation(context: Context): UserLocation {
        var locationObject = UserLocation();
        if (LocationPermissions.hasLocationPermission(context)) {
            LocationManager.getCurrentLocation { location ->
                location?.let {
                    locationObject = UserLocation(it.latitude.toString(), it.longitude.toString())
                } ?: run {
                    Log.e("Location", "Localização indisponível.")
                }
            }
        } else {
            Log.e("Location", "Permissão de localização não concedida.")
        }

        return locationObject
    }

}