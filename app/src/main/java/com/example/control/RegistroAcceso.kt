package com.example.control

data class RegistroAcceso(
    var id: String = "",
    val docente: String = "",
    val laboratorio: String = "",
    val curso: String = "",
    val fecha: String = "",
    val horaEntrada: String = "",
    val horaSalida: String? = null,
    val estado: String = ""
)
