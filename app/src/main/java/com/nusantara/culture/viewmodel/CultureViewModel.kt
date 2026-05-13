package com.nusantara.culture.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nusantara.culture.data.CultureCategory
import com.nusantara.culture.data.CultureItem
import com.nusantara.culture.data.CultureRepository

class CultureViewModel : ViewModel() {

    // ── Input state ──────────────────────────────────────────────────────────
    var userName by mutableStateOf("")
        private set

    var selectedCategory by mutableStateOf<CultureCategory?>(null)
        private set

    // ── Validation errors ────────────────────────────────────────────────────
    var userNameError by mutableStateOf<String?>(null)
        private set

    var categoryError by mutableStateOf<String?>(null)
        private set

    // ── Result state ─────────────────────────────────────────────────────────
    var searchResults by mutableStateOf<List<CultureItem>>(emptyList())
        private set

    var hasSearched by mutableStateOf(false)
        private set

    // ── Language ─────────────────────────────────────────────────────────────
    var isIndonesian by mutableStateOf(true)
        private set

    // ── Actions ──────────────────────────────────────────────────────────────
    fun onUserNameChange(value: String) {
        userName = value
        if (value.isNotBlank()) userNameError = null
    }

    fun onCategorySelected(category: CultureCategory) {
        selectedCategory = category
        categoryError = null
    }

    fun toggleLanguage() {
        isIndonesian = !isIndonesian
    }

    fun search(): Boolean {
        var valid = true

        if (userName.isBlank()) {
            userNameError = if (isIndonesian) "Nama tidak boleh kosong" else "Name cannot be empty"
            valid = false
        }

        if (selectedCategory == null) {
            categoryError = if (isIndonesian) "Pilih kategori budaya" else "Please select a culture category"
            valid = false
        }

        if (valid) {
            searchResults = CultureRepository.getByCategory(selectedCategory!!)
            hasSearched = true
        }

        return valid
    }

    fun reset() {
        userName = ""
        selectedCategory = null
        userNameError = null
        categoryError = null
        searchResults = emptyList()
        hasSearched = false
    }

    fun getGreeting(): String {
        return if (isIndonesian) "Halo, $userName!" else "Hello, $userName!"
    }

    fun getCategoryDisplayName(category: CultureCategory): String {
        return if (isIndonesian) category.displayNameId else category.displayName
    }
}
