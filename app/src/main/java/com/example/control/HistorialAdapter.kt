package com.example.control

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.graphics.Color

class HistorialAdapter (private val registros: List<RegistroAcceso>) : RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_historial, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val registro = registros[position]
        holder.bind(registro)
    }

    override fun getItemCount(): Int = registros.size

    inner class HistorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDocente: TextView = itemView.findViewById(R.id.tv_docente)
        private val tvLaboratorio: TextView = itemView.findViewById(R.id.tv_laboratorio)
        private val tvCurso: TextView = itemView.findViewById(R.id.tv_curso)
        private val tvFecha: TextView = itemView.findViewById(R.id.tv_fecha)
        private val tvHoraEntrada: TextView = itemView.findViewById(R.id.tv_hora_entrada)
        private val tvHoraSalida: TextView = itemView.findViewById(R.id.tv_hora_salida)
        private val tvEstado: TextView = itemView.findViewById(R.id.tv_estado)

        fun bind(registro: RegistroAcceso) {
            tvDocente.text = registro.docente
            tvLaboratorio.text = registro.laboratorio
            tvCurso.text = registro.curso
            tvFecha.text = registro.fecha
            tvHoraEntrada.text = registro.horaEntrada
            tvHoraSalida.text = registro.horaSalida ?: "--:--"
            tvEstado.text = registro.estado

            // Cambiar color según estado
            if (registro.estado == "Completado") {
                tvEstado.setTextColor(Color.GREEN)
            } else {
                tvEstado.setTextColor(Color.YELLOW)
            }
        }
    }
}