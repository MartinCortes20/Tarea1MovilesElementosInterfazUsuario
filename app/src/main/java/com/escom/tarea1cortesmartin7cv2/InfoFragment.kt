package com.escom.tarea1cortesmartin7cv2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class InfoFragment : Fragment() {

    private lateinit var progressHorizontal: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var btnIncreaseProgress: Button
    private lateinit var btnDecreaseProgress: Button
    private lateinit var btnResetProgress: Button
    private lateinit var tvDynamicContent: TextView
    private lateinit var btnUpdateContent: Button

    private var currentProgress = 65
    private val handler = Handler(Looper.getMainLooper())
    private var updateRunnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupClickListeners()
        startDynamicUpdates()
    }

    private fun initializeViews(view: View) {
        progressHorizontal = view.findViewById(R.id.progressHorizontal)
        tvProgress = view.findViewById(R.id.tvProgress)
        btnIncreaseProgress = view.findViewById(R.id.btnIncreaseProgress)
        btnDecreaseProgress = view.findViewById(R.id.btnDecreaseProgress)
        btnResetProgress = view.findViewById(R.id.btnResetProgress)
        tvDynamicContent = view.findViewById(R.id.tvDynamicContent)
        btnUpdateContent = view.findViewById(R.id.btnUpdateContent)
    }

    private fun setupClickListeners() {
        btnIncreaseProgress.setOnClickListener {
            increaseProgress()
        }

        btnDecreaseProgress.setOnClickListener {
            decreaseProgress()
        }

        btnResetProgress.setOnClickListener {
            resetProgress()
        }

        btnUpdateContent.setOnClickListener {
            updateDynamicContent()
        }
    }

    private fun increaseProgress() {
        if (currentProgress < 100) {
            currentProgress += 10
            updateProgressBar()
            showToast("Progreso aumentado: $currentProgress%")
        } else {
            showToast("¡Progreso al máximo!")
        }
    }

    private fun decreaseProgress() {
        if (currentProgress > 0) {
            currentProgress -= 10
            updateProgressBar()
            showToast("Progreso disminuido: $currentProgress%")
        } else {
            showToast("¡Progreso al mínimo!")
        }
    }

    private fun resetProgress() {
        currentProgress = 0
        updateProgressBar()
        showToast("Progreso reiniciado")
    }

    private fun updateProgressBar() {
        progressHorizontal.progress = currentProgress
        tvProgress.text = "Progreso: $currentProgress%"

        // Cambiar color basado en el progreso
        val color = when {
            currentProgress < 30 -> "#F44336" // Rojo
            currentProgress < 70 -> "#FF9800" // Naranja
            else -> "#4CAF50" // Verde
        }

        // Note: Changing progress bar color programmatically requires API level consideration
        // For simplicity, we'll just update the text
    }

    private fun updateDynamicContent() {
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val randomMessages = listOf(
            "🕒 Actualizado a las $currentTime",
            "📊 Estado del sistema: Operativo",
            "🔋 Batería: ${(20..100).random()}%",
            "📶 Señal: ${(1..5).random()}/5 barras",
            "💾 Memoria libre: ${(1..8).random()} GB",
            "🌡️ Temperatura: ${(18..35).random()}°C",
            "📡 Conectado a WiFi",
            "⚡ Rendimiento: Óptimo"
        )

        val randomMessage = randomMessages.random()
        tvDynamicContent.text = randomMessage
        showToast("Contenido actualizado")
    }

    private fun startDynamicUpdates() {
        updateRunnable = object : Runnable {
            override fun run() {
                // Actualizar contenido automáticamente cada 5 segundos
                if (isAdded && view != null) {
                    val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

                    val autoMessages = listOf(
                        "⏰ Hora actual: $currentTime",
                        "🔄 Actualización automática",
                        "📱 App funcionando correctamente",
                        "💡 TextView puede mostrar cualquier información",
                        "🎯 Los elementos de información son versátiles",
                        "📋 Datos actualizándose en tiempo real"
                    )

                    tvDynamicContent.text = autoMessages.random()

                    // Programar próxima actualización
                    handler.postDelayed(this, 5000)
                }
            }
        }
        handler.postDelayed(updateRunnable!!, 5000)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpiar el handler para evitar memory leaks
        updateRunnable?.let { handler.removeCallbacks(it) }
    }
}