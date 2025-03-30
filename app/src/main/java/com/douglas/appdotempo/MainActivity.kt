package com.douglas.appdotempo

import AppTempoNavHost
import androidx.navigation.compose.navigation
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import android.os.Bundle
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


class MainActivity : ComponentActivity() {



    // API do google para pegar localização do usuario
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {

        //Solicitação de permissão
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        permissaoLoc()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .safeDrawingPadding()
            ){
                AppDoTempoTheme {
                    AppTempoNavHost()
                }
            }
        }
    }

    private fun permissaoLoc(){

        val permissionLaucher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){aceito: Boolean ->
            if (aceito){
                // Permissao aceita
                getloc()
            }else{
                // Permissão Negada colocar msg
            }
        }

        // Verifica se tem permissão
        if (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            permissionLaucher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else{
            getloc()
        }

    }

    public var lastLatLong: LatLong? = null

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    public fun getloc(): LatLong?{
        fusedLocationClient.lastLocation.addOnSuccessListener{ loc: Location? ->
            loc?.let {
                lastLatLong = LatLong(it.longitude, it.latitude)
            }
        }

        return lastLatLong
    }
}



