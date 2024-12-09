package com.example.control

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class HistorialActivity: AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var historialAdapter: HistorialAdapter
    private lateinit var rvHistorial: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        firestore = FirebaseFirestore.getInstance()
        rvHistorial = findViewById(R.id.rv_historial)

        // Configurar RecyclerView
        rvHistorial.layoutManager = LinearLayoutManager(this)

        // Obtener los registros de acceso desde Firestore
        obtenerHistorial()
    }

    private fun obtenerHistorial() {
        firestore.collection("registros_acceso")
            .get()
            .addOnSuccessListener { documents ->
                val registros = mutableListOf<RegistroAcceso>()
                for (document in documents) {
                    val registro = document.toObject(RegistroAcceso::class.java)
                    registro.id = document.id // Añadir el ID del documento
                    registros.add(registro)
                }
                // Configurar el adaptador
                historialAdapter = HistorialAdapter(registros)
                rvHistorial.adapter = historialAdapter
            }
            .addOnFailureListener { e ->

                Toast.makeText(this, "Error al cargar el historial", Toast.LENGTH_SHORT).show()
            }
    }
}