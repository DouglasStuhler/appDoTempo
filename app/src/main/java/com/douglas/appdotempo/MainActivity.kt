package com.douglas.appdotempo

import AppTempoNavHost
import androidx.navigation.compose.navigation
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import com.douglas.appdotempo.data.LatLong
import com.douglas.appdotempo.domain.previsao1
import com.douglas.appdotempo.domain.previsao2
import com.douglas.appdotempo.ui.components.labelDiaAtual
import com.douglas.appdotempo.ui.components.resumoPrev
import com.douglas.appdotempo.ui.theme.AppDoTempoTheme
import com.douglas.appdotempo.ui.theme.azul_dia
import com.google.android.gms.location.LocationRequest


class MainActivity : ComponentActivity() {


    // API do google para pegar localização do usuario
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private var locationCallback: LocationCallback? = null
    private var currentLocation: LatLong? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        //Solicitação de permissão
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        super.onCreate(savedInstanceState)
        permissaoLoc()

    }

    fun getloc(): LatLong?{
        startLocationUpdates()
        return currentLocation
    }

   private fun permissaoLoc(){

        val permissionLaucher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){aceito: Boolean ->
            if (aceito){
                startLocationUpdates()
            }else{
                currentLocation = LatLong(55.45, 37.37)
            }
        }

        // Verifica se tem permissão
        if (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            permissionLaucher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else{
            startLocationUpdates()
        }

    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(){
        Log.d("Abacaxi", "Entrou Start Location")
        val  locationRequest = LocationRequest.create().apply{
            interval = 0
            fastestInterval = 0
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }


        locationCallback = object  : LocationCallback (){

            override fun onLocationResult(locationResult: LocationResult){
                super .onLocationResult(locationResult)
                locationResult.lastLocation?.let { location ->
                    currentLocation = LatLong(location.latitude, location.longitude)
                    Log.d("Abacaxi", "Nova Localização: $currentLocation")
                    enableEdgeToEdge()
                    setContent {
                        Box(
                            modifier = Modifier.safeDrawingPadding()
                        ){
                            AppDoTempoTheme {
                                AppTempoNavHost(currentLocation)
                            }
                        }
                    }
                }
            }

        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback as LocationCallback,
            Looper.getMainLooper()
        )

        fusedLocationClient.lastLocation.addOnSuccessListener { location -> location?.let{
            currentLocation = LatLong(it.latitude, it.longitude)
        } }

        Log.d("Abacaxi", "Começou criação de tela")
        Log.d("Abacaxi", "$currentLocation")

    }

    override fun onDestroy() {
        super.onDestroy()
        locationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }
    }

}



