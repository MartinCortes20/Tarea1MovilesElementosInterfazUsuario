package com.escom.tarea1cortesmartin7cv2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var btnTextFields: Button
    private lateinit var btnButtons: Button
    private lateinit var btnSelection: Button
    private lateinit var btnLists: Button
    private lateinit var btnInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupClickListeners()

        // Mostrar el primer fragment por defecto
        loadFragment(TextFieldsFragment())
        setActiveButton(btnTextFields)
    }

    private fun initializeViews() {
        btnTextFields = findViewById(R.id.btnTextFields)
        btnButtons = findViewById(R.id.btnButtons)
        btnSelection = findViewById(R.id.btnSelection)
        btnLists = findViewById(R.id.btnLists)
        btnInfo = findViewById(R.id.btnInfo)
    }

    private fun setupClickListeners() {
        btnTextFields.setOnClickListener {
            loadFragment(TextFieldsFragment())
            setActiveButton(btnTextFields)
        }

        btnButtons.setOnClickListener {
            loadFragment(ButtonsFragment())
            setActiveButton(btnButtons)
        }

        btnSelection.setOnClickListener {
            loadFragment(SelectionFragment())
            setActiveButton(btnSelection)
        }

        btnLists.setOnClickListener {
            loadFragment(ListsFragment())
            setActiveButton(btnLists)
        }

        btnInfo.setOnClickListener {
            loadFragment(InfoFragment())
            setActiveButton(btnInfo)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun setActiveButton(activeButton: Button) {
        // Resetear todos los botones
        val buttons = listOf(btnTextFields, btnButtons, btnSelection, btnLists, btnInfo)
        buttons.forEach { button ->
            // CORRECCIÓN: Usar ContextCompat para compatibilidad
            button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        }

        // Activar el botón seleccionado
        // CORRECCIÓN: Usar un color de tu archivo colors.xml
        activeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
    }
}