package com.example.taller1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide

class pais_info : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pais_info)

        val extras = intent.extras

        val nombreNativo = extras?.getString("NativeName")
        val nombrePais = extras?.getString("Name")
        val region = extras?.getString("Region")
        val subRegion = extras?.getString("SubRegion")
        val currencyCode = extras?.getString("CurrencyCode")
        val currencyName = extras?.getString("CurrencyName")
        val currencySymbol = extras?.getString("CurrencySymbol")
        val codigo = extras?.getString("NumericCode")

        var tnombreNativo = findViewById<TextView>(R.id.nombreNativo).apply {
            text = "Nombre nativo: " + nombreNativo
        }
        var tnombrePais = findViewById<TextView>(R.id.nombrePais).apply {
            text = nombrePais
        }
        var tregion = findViewById<TextView>(R.id.region).apply {
            text = "Region: " + region
        }
        var tsubRegion = findViewById<TextView>(R.id.subRegion).apply {
            text = "Subregion: " + subRegion
        }
        var tcurrencyCode = findViewById<TextView>(R.id.currencyCode).apply {
            text = "Codigo de moneda: " + currencyCode
        }
        var tcurrencyName = findViewById<TextView>(R.id.currencyName).apply {
            text = "Nombre de moneda: " + currencyName
        }
        var tcurrencySymbol = findViewById<TextView>(R.id.currencySymbol).apply {
            text = "Simbolo de moneda: " + currencySymbol
        }
        var tcodigo = findViewById<Button>(R.id.callButton).apply {
            text = "+" + codigo
        }


        //Imagen
        val width = 900 //Ancho
        val height = 900 //Alto
        val imagen = extras?.getString("FlagPng")
        Glide.with(this).load(imagen).override(width, height).fitCenter().into(findViewById(R.id.bandera))


/*
        val webView = findViewById<WebView>(R.id.webView)
        //crear el url para la bandera
        val url="https://www.countryflags.com/flag-of-"+nombrePais+"/"
        webView.webViewClient= WebViewClient()
        webView.apply {
            webView.loadUrl(url)

            settings.javaScriptEnabled=true
            settings.safeBrowsingEnabled =true
            settings.textZoom = -200
        }
        webView.scrollTo(0, 400)
        webView.isClickable = false
        webView.isLongClickable = false
        webView.isFocusable = false
        webView.isFocusableInTouchMode = false
        webView.isScrollContainer= false
*/
        /*Cambio a Telefono*/
        val callButton = findViewById<Button>(R.id.callButton)
        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${codigo}")
            startActivity(intent)
        }

    }
}