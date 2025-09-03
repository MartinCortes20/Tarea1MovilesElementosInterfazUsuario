package com.escom.tarea1cortesmartin7cv2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class ListsFragment : Fragment() {

    private lateinit var btnAddItem: Button
    private lateinit var btnClearList: Button
    private lateinit var etNewItem: EditText
    private lateinit var listView: ListView
    private lateinit var tvItemCount: TextView
    private lateinit var tvSelectedItem: TextView

    private val items = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupListView()
        setupClickListeners()
        loadSampleData()
    }

    private fun initializeViews(view: View) {
        btnAddItem = view.findViewById(R.id.btnAddItem)
        btnClearList = view.findViewById(R.id.btnClearList)
        etNewItem = view.findViewById(R.id.etNewItem)
        listView = view.findViewById(R.id.listView)
        tvItemCount = view.findViewById(R.id.tvItemCount)
        tvSelectedItem = view.findViewById(R.id.tvSelectedItem)
    }

    private fun setupListView() {
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            items
        )
        listView.adapter = adapter

        // Listener para clicks en elementos de la lista
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            tvSelectedItem.text = "Seleccionado: $selectedItem"
            showToast("Seleccionaste: $selectedItem")
        }

        // Listener para clicks largos (eliminar elemento)
        listView.setOnItemLongClickListener { _, _, position, _ ->
            val itemToRemove = items[position]
            items.removeAt(position)
            adapter.notifyDataSetChanged()
            updateItemCount()
            showToast("Eliminado: $itemToRemove")
            tvSelectedItem.text = "Elemento eliminado"
            true
        }
    }

    private fun setupClickListeners() {
        btnAddItem.setOnClickListener {
            addNewItem()
        }

        btnClearList.setOnClickListener {
            clearList()
        }

        // Permitir agregar elemento presionando Enter
        etNewItem.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_ENTER &&
                event.action == android.view.KeyEvent.ACTION_DOWN) {
                addNewItem()
                true
            } else {
                false
            }
        }
    }

    private fun loadSampleData() {
        val sampleItems = listOf(
            "📱 Android Studio",
            "🎨 Diseño UI/UX",
            "🛠️ Kotlin",
            "📊 Base de datos",
            "🌐 APIs REST",
            "🔐 Autenticación",
            "📸 Cámara y galería",
            "🗺️ Mapas y ubicación"
        )

        items.addAll(sampleItems)
        adapter.notifyDataSetChanged()
        updateItemCount()
    }

    private fun addNewItem() {
        val newItem = etNewItem.text.toString().trim()

        if (newItem.isNotEmpty()) {
            // Agregar emoji aleatorio al inicio
            val emojis = listOf("✨", "🎯", "💡", "⭐", "🚀", "💎", "🔥", "🎉")
            val randomEmoji = emojis.random()
            val formattedItem = "$randomEmoji $newItem"

            items.add(formattedItem)
            adapter.notifyDataSetChanged()
            etNewItem.text.clear()
            updateItemCount()

            // Hacer scroll al nuevo elemento
            listView.smoothScrollToPosition(items.size - 1)

            showToast("Agregado: $formattedItem")
            tvSelectedItem.text = "Último agregado: $formattedItem"
        } else {
            showToast("Escribe algo para agregar")
        }
    }

    private fun clearList() {
        val itemCount = items.size
        items.clear()
        adapter.notifyDataSetChanged()
        updateItemCount()
        tvSelectedItem.text = "Lista limpiada"
        showToast("Se eliminaron $itemCount elementos")
    }

    private fun updateItemCount() {
        tvItemCount.text = "Elementos: ${items.size}"
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}