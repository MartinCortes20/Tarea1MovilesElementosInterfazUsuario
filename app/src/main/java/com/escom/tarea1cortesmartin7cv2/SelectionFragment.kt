package com.escom.tarea1cortesmartin7cv2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class SelectionFragment : Fragment() {

    private lateinit var cbOption1: CheckBox
    private lateinit var cbOption2: CheckBox
    private lateinit var cbOption3: CheckBox
    private lateinit var rgColors: RadioGroup
    private lateinit var rbRed: RadioButton
    private lateinit var rbGreen: RadioButton
    private lateinit var rbBlue: RadioButton
    private lateinit var swNotifications: Switch
    private lateinit var swDarkMode: Switch
    private lateinit var swLocation: Switch
    private lateinit var spinnerCountries: Spinner
    private lateinit var btnShowSelections: Button
    private lateinit var tvSelectionResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupSpinner()
        setupListeners()
    }

    private fun initializeViews(view: View) {
        cbOption1 = view.findViewById(R.id.cbOption1)
        cbOption2 = view.findViewById(R.id.cbOption2)
        cbOption3 = view.findViewById(R.id.cbOption3)
        rgColors = view.findViewById(R.id.rgColors)
        rbRed = view.findViewById(R.id.rbRed)
        rbGreen = view.findViewById(R.id.rbGreen)
        rbBlue = view.findViewById(R.id.rbBlue)
        swNotifications = view.findViewById(R.id.swNotifications)
        swDarkMode = view.findViewById(R.id.swDarkMode)
        swLocation = view.findViewById(R.id.swLocation)
        spinnerCountries = view.findViewById(R.id.spinnerCountries)
        btnShowSelections = view.findViewById(R.id.btnShowSelections)
        tvSelectionResult = view.findViewById(R.id.tvSelectionResult)
    }

    private fun setupSpinner() {
        val countries = arrayOf(
            "Selecciona un país...",
            "🇲🇽 México",
            "🇺🇸 Estados Unidos",
            "🇨🇦 Canadá",
            "🇪🇸 España",
            "🇦🇷 Argentina",
            "🇨🇴 Colombia",
            "🇧🇷 Brasil"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            countries
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountries.adapter = adapter
    }

    private fun setupListeners() {
        btnShowSelections.setOnClickListener {
            showAllSelections()
        }

        // Listeners para cambios en tiempo real
        cbOption1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) showToast("✅ Desarrollo de Apps seleccionado")
        }

        cbOption2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) showToast("✅ Desarrollo Web seleccionado")
        }

        cbOption3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) showToast("✅ Desarrollo de Juegos seleccionado")
        }

        rgColors.setOnCheckedChangeListener { _, checkedId ->
            val color = when (checkedId) {
                R.id.rbRed -> "🔴 Rojo"
                R.id.rbGreen -> "🟢 Verde"
                R.id.rbBlue -> "🔵 Azul"
                else -> "Ninguno"
            }
            showToast("Color seleccionado: $color")
        }

        swNotifications.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "activadas" else "desactivadas"
            showToast("🔔 Notificaciones $status")
        }

        swDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "activado" else "desactivado"
            showToast("🌙 Modo oscuro $status")
        }

        swLocation.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "activada" else "desactivada"
            showToast("📍 Ubicación $status")
        }
    }

    private fun showAllSelections() {
        val result = buildString {
            append("☑️ SELECCIONES ACTUALES:\n\n")

            // CheckBoxes
            append("📱 INTERESES:\n")
            if (cbOption1.isChecked) append("• Desarrollo de Apps\n")
            if (cbOption2.isChecked) append("• Desarrollo Web\n")
            if (cbOption3.isChecked) append("• Desarrollo de Juegos\n")
            if (!cbOption1.isChecked && !cbOption2.isChecked && !cbOption3.isChecked) {
                append("• Ninguno seleccionado\n")
            }
            append("\n")

            // RadioButton
            val selectedColorId = rgColors.checkedRadioButtonId
            val selectedColor = when (selectedColorId) {
                R.id.rbRed -> "🔴 Rojo"
                R.id.rbGreen -> "🟢 Verde"
                R.id.rbBlue -> "🔵 Azul"
                else -> "Ninguno"
            }
            append("🎨 COLOR FAVORITO: $selectedColor\n\n")

            // Switches
            append("⚙️ CONFIGURACIÓN:\n")
            append("• Notificaciones: ${if (swNotifications.isChecked) "✅ ON" else "❌ OFF"}\n")
            append("• Modo Oscuro: ${if (swDarkMode.isChecked) "✅ ON" else "❌ OFF"}\n")
            append("• Ubicación: ${if (swLocation.isChecked) "✅ ON" else "❌ OFF"}\n\n")

            // Spinner
            val selectedCountry = spinnerCountries.selectedItem.toString()
            append("🌍 PAÍS: $selectedCountry")
        }

        tvSelectionResult.text = result
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}