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
import java.time.LocalDate
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    // Instancia de Firestore
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar Firestore
        firestore = FirebaseFirestore.getInstance()

        // Referencia al botón
        val btnSubirRegistro = findViewById<Button>(R.id.btnRegistrar)

        // Evento del botón
        btnSubirRegistro.setOnClickListener {
            // Aquí puedes obtener los valores dinámicamente, por ejemplo, de un formulario o argumentos
            val docenteId = "D001" // Reemplaza con el ID real
            val laboratorioId = "Lab301" // Reemplaza con el ID real
            registrarAcceso(docenteId, laboratorioId)
        }
    }

    private fun registrarAcceso(docenteId: String, laboratorioId: String) {
        // Obtener instancia de Firestore
        val db = FirebaseFirestore.getInstance()
        val fechaActual = LocalDate.now().toString() // Formato: "YYYY-MM-DD"
        val horaActual = LocalTime.now().toString() // Formato: "HH:mm:ss"
        // Crear el registro
        val registro = hashMapOf(
            "id_docente" to docenteId,           // ID del docente
            "laboratorio_id" to laboratorioId,     // ID del laboratorio
            "curso" to "ingenieria de software", // Curso asignado
            "fecha" to fechaActual,          // Fecha del registro
            "hora_entrada" to horaActual,        // Hora de entrada
            "hora_salida" to null,            // Hora de salida (inicialmente null)
            "estado" to "En curso"            // Estado del acceso
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