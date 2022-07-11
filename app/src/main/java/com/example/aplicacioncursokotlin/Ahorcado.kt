package com.example.aplicacioncursokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class Ahorcado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ahorcado)
        val palabra = findViewById<TextView>(R.id.textViewPalabra)
        val empezar = findViewById<Button>(R.id.buttonEmpezar)
        val buscar = findViewById<Button>(R.id.buttonBuscar)
        val letra = findViewById<EditText>(R.id.editTextLetra)
        val fallos = findViewById<TextView>(R.id.textViewFallos)
        val aciertos = findViewById<TextView>(R.id.textViewAciertos)
        val mensaje = findViewById<TextView>(R.id.textViewMensaje)
        val imagen = findViewById<ImageView>(R.id.imagen)
        val abecedario = mutableListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
        "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        val diccionario = arrayOf("ALFOMBRA", "PEINE", "DIBUJO", "ORDENADOR", "PROCESADOR")
        var resultado = ""
        val acertadas = mutableListOf<String>()
        val falladas = mutableListOf<String>()
        var contador = 0


        empezar.setOnClickListener {
            mensaje.text = ""
            palabra.text = ""
            aciertos.text = ""
            fallos.text = ""
            acertadas.clear()
            falladas.clear()
            imagen.setImageResource(R.drawable.ahorcado0)
            val azar = Random.nextInt(0, diccionario.count())
            resultado = diccionario[azar]
            for (i in 0..resultado.length - 1) {
                palabra.text = palabra.text.toString() + "_ "
            }
        }
        buscar.setOnClickListener {
            mensaje.text = ""
            if (letra.text.toString().uppercase() in abecedario) {
                if (letra.text.toString().uppercase() in falladas || letra.text.toString().uppercase() in acertadas){
                    mensaje.text = "Ya has probado esta letra. Introduce otra."}
                else if (letra.text.toString().uppercase() in resultado){
                    aciertos.text = ""
                    palabra.text = ""
                    acertadas.add(letra.text.toString().uppercase())
                    for (l in acertadas){
                        aciertos.text = aciertos.text.toString() + l + " "
                    }
                    for (l in resultado){
                        if (l.toString() == letra.text.toString().uppercase()){
                            palabra.text = palabra.text.toString() + l.toString()
                        }
                        else if (l.toString() in acertadas){
                            palabra.text = palabra.text.toString() + l.toString()
                        }
                        else{
                            palabra.text = palabra.text.toString() + "_ "
                        }
                    }
                    if (palabra.text.toString() == resultado){
                        mensaje.text = "Felicidades! Has acertado la palabra."
                    }
                }
                else {
                    contador ++
                    fallos.text = ""
                    falladas.add(letra.text.toString().uppercase())
                    for (l in falladas){
                        fallos.text = fallos.text.toString() + l + " "
                    }
                    when (contador){
                        1 -> imagen.setImageResource(R.drawable.ahorcado1)
                        2 -> imagen.setImageResource(R.drawable.ahorcado2)
                        3 -> imagen.setImageResource(R.drawable.ahorcado3)
                        4 -> imagen.setImageResource(R.drawable.ahorcado4)
                        5 -> imagen.setImageResource(R.drawable.ahorcado5)
                        6 -> imagen.setImageResource(R.drawable.ahorcado6)
                    }
                    mensaje.text = "La letra no se encuentra en la palabra."
                    if (contador == 6){
                        mensaje.text = "Has perdido, la palabra era $resultado."
                    }
                }
            }
            else {
                mensaje.text = "Introduce una sola letra."
            }
        }
    }
}
