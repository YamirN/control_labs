package com.example.control

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los elementos del layout
        val inputDocente = findViewById<EditText>(R.id.inputDocente)
        val inputLaboratorio = findViewById<EditText>(R.id.inputLaboratorio)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        // Configurar el evento del botón
        btnRegistrar.setOnClickListener {
            val docenteId = inputDocente.text.toString().trim()
            val laboratorioId = inputLaboratorio.text.toString().trim()

            // Validar campos vacíos
            if (docenteId.isEmpty() || laboratorioId.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registrar el acceso
            registrarAcceso(docenteId, laboratorioId)
        }
    }

    private fun registrarAcceso(docenteId: String, laboratorioId: String) {
        // Obtener instancia de Firestore
        val db = FirebaseFirestore.getInstance()

        // Crear el registro
        val registro = hashMapOf(
            "docente_id" to docenteId,
            "laboratorio_id" to laboratorioId,
            "fecha" to FieldValue.serverTimestamp(),
            "estado" to "Pendiente" // Estado inicial
        )

        // Guardar en Firestore
        db.collection("registros_acceso")
            .add(registro)
            .addOnSuccessListener {
                Toast.makeText(this, "Acceso registrado con éxito", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al registrar acceso: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}