package com.escom.tarea1cortesmartin7cv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class TextFieldsFragment : Fragment() {

    private lateinit var etBasic: EditText
    private lateinit var etWithIcon: EditText
    private lateinit var etPassword: EditText
    private lateinit var etNumber: EditText
    private lateinit var etMultiline: EditText
    private lateinit var btnShowText: Button
    private lateinit var tvResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_textfields, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupClickListeners()
    }

    private fun initializeViews(view: View) {
        etBasic = view.findViewById(R.id.etBasic)
        etWithIcon = view.findViewById(R.id.etWithIcon)
        etPassword = view.findViewById(R.id.etPassword)
        etNumber = view.findViewById(R.id.etNumber)
        etMultiline = view.findViewById(R.id.etMultiline)
        btnShowText = view.findViewById(R.id.btnShowText)
        tvResult = view.findViewById(R.id.tvResult)
    }

    private fun setupClickListeners() {
        btnShowText.setOnClickListener {
            val basic = etBasic.text.toString()
            val withIcon = etWithIcon.text.toString()
            val password = etPassword.text.toString()
            val number = etNumber.text.toString()
            val multiline = etMultiline.text.toString()

            val result = buildString {
                append("📝 TEXTO INGRESADO:\n\n")
                append("Básico: ${if (basic.isNotEmpty()) basic else "Sin texto"}\n")
                append("Con ícono: ${if (withIcon.isNotEmpty()) withIcon else "Sin texto"}\n")
                append("Contraseña: ${if (password.isNotEmpty()) "*".repeat(password.length) else "Sin contraseña"}\n")
                append("Número: ${if (number.isNotEmpty()) number else "Sin número"}\n")
                append("Multilinea: ${if (multiline.isNotEmpty()) multiline else "Sin texto"}")
            }

            tvResult.text = result
        }
    }
}