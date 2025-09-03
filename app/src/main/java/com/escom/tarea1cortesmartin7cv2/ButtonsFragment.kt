package com.escom.tarea1cortesmartin7cv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class ButtonsFragment : Fragment() {

    private lateinit var btnBasic: Button
    private lateinit var btnOutlined: Button
    private lateinit var ibPlay: ImageButton
    private lateinit var ibPause: ImageButton
    private lateinit var ibStop: ImageButton
    private lateinit var toggleButton: ToggleButton
    private lateinit var btnSuccess: Button
    private lateinit var btnWarning: Button
    private lateinit var btnDanger: Button
    private lateinit var btnCounter: Button
    private lateinit var tvButtonResult: TextView

    private var clickCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupClickListeners()
    }

    private fun initializeViews(view: View) {
        btnBasic = view.findViewById(R.id.btnBasic)
        btnOutlined = view.findViewById(R.id.btnOutlined)
        ibPlay = view.findViewById(R.id.ibPlay)
        ibPause = view.findViewById(R.id.ibPause)
        ibStop = view.findViewById(R.id.ibStop)
        toggleButton = view.findViewById(R.id.toggleButton)
        btnSuccess = view.findViewById(R.id.btnSuccess)
        btnWarning = view.findViewById(R.id.btnWarning)
        btnDanger = view.findViewById(R.id.btnDanger)
        btnCounter = view.findViewById(R.id.btnCounter)
        tvButtonResult = view.findViewById(R.id.tvButtonResult)
    }

    private fun setupClickListeners() {
        btnBasic.setOnClickListener {
            showResult("Botón Básico presionado")
            showToast("¡Botón básico!")
        }

        btnOutlined.setOnClickListener {
            showResult("Botón con Borde presionado")
            showToast("¡Botón outlined!")
        }

        ibPlay.setOnClickListener {
            showResult("▶️ Reproducir presionado")
            showToast("▶️ Reproduciendo...")
        }

        ibPause.setOnClickListener {
            showResult("⏸️ Pausar presionado")
            showToast("⏸️ Pausado")
        }

        ibStop.setOnClickListener {
            showResult("⏹️ Detener presionado")
            showToast("⏹️ Detenido")
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "ACTIVADO ✅" else "DESACTIVADO ❌"
            showResult("Toggle Button: $status")
        }

        btnSuccess.setOnClickListener {
            showResult("✅ Botón de Éxito presionado")
            showToast("✅ ¡Éxito!")
        }

        btnWarning.setOnClickListener {
            showResult("⚠️ Botón de Alerta presionado")
            showToast("⚠️ ¡Cuidado!")
        }

        btnDanger.setOnClickListener {
            showResult("❌ Botón de Peligro presionado")
            showToast("❌ ¡Peligro!")
        }

        btnCounter.setOnClickListener {
            clickCount++
            btnCounter.text = "Clics: $clickCount"
            showResult("Contador actualizado: $clickCount clics")
        }
    }

    private fun showResult(message: String) {
        tvButtonResult.text = "🔘 ACCIÓN: $message\nHora: ${System.currentTimeMillis()}"
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}