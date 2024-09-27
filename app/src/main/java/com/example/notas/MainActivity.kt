package com.example.notas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtNota1: EditText
    private lateinit var txtNota2: EditText
    private lateinit var txtNota3: EditText
    private lateinit var txtNota4: EditText
    private lateinit var txtPromedio: TextView
    private lateinit var txtMayor: TextView
    private lateinit var txtMenor: TextView

    private var nota1 = 0
    private var nota2 = 0
    private var nota3 = 0
    private var nota4 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtPromedio = findViewById(R.id.txtPromedio)
        txtMayor = findViewById(R.id.txtMayor)
        txtMenor = findViewById(R.id.txtMenor)

        // Asegúrate de inicializar las referencias a los EditText
        txtNota1 = findViewById(R.id.txtNota1)
        txtNota2 = findViewById(R.id.txtNota2)
        txtNota3 = findViewById(R.id.txtNota3)
        txtNota4 = findViewById(R.id.txtNota4)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            promedio()
        }
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            mayor()
        }
        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            menor()
        }
    }

    private fun obtenerValores() {
        nota1 = txtNota1.text.toString().toIntOrNull() ?: 0
        nota2 = txtNota2.text.toString().toIntOrNull() ?: 0
        nota3 = txtNota3.text.toString().toIntOrNull() ?: 0
        nota4 = txtNota4.text.toString().toIntOrNull() ?: 0
    }

    private fun esValido(valor: Int): Boolean {
        return valor in 0..20

    }
    @SuppressLint("SetTextI18n")
    fun promedio() {
        obtenerValores()
        if (esValido(nota1) && esValido(nota2) && esValido(nota3) && esValido(nota4)) {
            val prom = (nota1 + nota2 + nota3 + nota4) / 4.0
            if (prom >= 12) {
                txtPromedio.text = "Aprobado con $prom"
                Toast.makeText(this, "Felicidades", Toast.LENGTH_SHORT).show()
            } else {
                txtPromedio.text = "Reprobado con $prom"
                Toast.makeText(this, "Lo sentimos", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ingrese notas válidas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mayor() {
        obtenerValores()
        if (esValido(nota1) && esValido(nota2) && esValido(nota3) && esValido(nota4)) {
            val lista = listOf(nota1, nota2, nota3, nota4)
            val may = lista.maxOrNull() ?: 0
            txtMayor.text = may.toString()
        } else {
            Toast.makeText(this, "Ingrese notas válidas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun menor() {
        obtenerValores()
        if (esValido(nota1) && esValido(nota2) && esValido(nota3) && esValido(nota4)) {
            val lista = listOf(nota1, nota2, nota3, nota4)
            val men = lista.minOrNull() ?: 0
            txtMenor.text = men.toString()
        } else {
            Toast.makeText(this, "Ingrese notas válidas", Toast.LENGTH_SHORT).show()
        }
    }
}