package com.example.futurobuscartelas.location

import android.Manifest
import android.content.Context
import androidx.core.app.ActivityCompat

object LocationPermissions {
    fun hasLocationPermission(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}