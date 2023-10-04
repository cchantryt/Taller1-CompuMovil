package com.example.taller1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class lista_paises : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_paises)

        var arrAux=""
        val json=JSONObject(LoadJSONFromAsset())
        val paisesJSONArray=json.getJSONArray("Countries")
        for (i in 0 until paisesJSONArray.length())
        {

            val jsonObject =paisesJSONArray.getJSONObject(i)
            val Name = jsonObject.getString("Name")
            val NativeName = jsonObject.getString("NativeName")
            val Region = jsonObject.getString("Region")
            val SubRegion = jsonObject.getString("SubRegion")
            val CurrencyCode = jsonObject.getString("CurrencyCode")
            val CurrencyName = jsonObject.getString("CurrencyName")
            val CurrencySymbol = jsonObject.getString("CurrencySymbol")
            val Flag = jsonObject.getString("FlagPng")
            val NumericCode = jsonObject.getString("NumericCode")

            var pais = Pais(Name, NativeName, Region, SubRegion, CurrencyCode, CurrencyName, CurrencySymbol, Flag, NumericCode)
            arrAux=arrAux+NativeName
            arrAux = arrAux + "," //separador

            paisesJSONArray.put(pais)
        }
        val arrAux2 = arrAux.substring(0, arrAux.length - 1)//quitar ultimo caracter
        val nombresPaises = arrAux2.split(",") //usar separador

        println(arrAux2)


        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1, nombresPaises)

        val ListView: ListView = findViewById(R.id.listView)

        ListView.adapter =adapter

        ListView.setOnItemClickListener { parent, view, position, id ->
            val paisEsc = paisesJSONArray.getJSONObject(position)   //buscar objeto con la posicion del nombre

            inforPais(paisEsc)
        }


    }

    private fun inforPais(paisEsc: JSONObject) {
        val intent = Intent(this, pais_info::class.java)

        // Add the strings to the intent's extras
        intent.putExtra("Name",paisEsc.get("Name").toString() )
        intent.putExtra("NativeName",paisEsc.get("NativeName").toString() )
        intent.putExtra("Region",paisEsc.get("Region").toString() )
        intent.putExtra("SubRegion",paisEsc.get("SubRegion").toString() )
        intent.putExtra("CurrencyCode",paisEsc.get("CurrencyCode").toString() )
        intent.putExtra("CurrencyName",paisEsc.get("CurrencyName").toString() )
        intent.putExtra("CurrencySymbol",paisEsc.get("CurrencySymbol").toString() )
        intent.putExtra("FlagPng",paisEsc.get("FlagPng").toString() )
        intent.putExtra("NumericCode",paisEsc.get("NumericCode").toString() )

        // Start the new activity
        startActivity(intent)
    }

    fun LoadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val istream: InputStream = assets.open("paises.json")
            val size: Int = istream.available()
            val buffer = ByteArray(size)
            istream.read(buffer)
            istream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
}