package com.example.movieappactivity.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.movieappactivity.data.DataStoreManager
import com.example.movieappactivity.repository.MyDBContainer
import com.example.movieappactivity.ui.ListScreen
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun ButtonLogin(
        email: String,
        password: String,
        context: Context,
        navController: NavController,
        dataStore: DataStoreManager
    ) {
        // Run blocking or View Model Scope
        viewModelScope.launch {
            val token = MyDBContainer().movieDBRepository.login(email, password)
            if (token.equals("Incorrect Password", true)) {
                Toast.makeText(context, token, Toast.LENGTH_LONG).show()
            } else if (token.equals("User Not Found", true)) {
                Toast.makeText(context, token, Toast.LENGTH_LONG).show()
            } else {
                navController.navigate(ListScreen.ListMovie.name) {
                    popUpTo(ListScreen.Login.name) { inclusive = true }
                }
                dataStore.saveToken(token)
                dataStore.getToken.collect{token ->
                    if (token != null){
                        MyDBContainer.ACCESS_TOKEN = token
                    }
                }
            }
        }
    }

    fun ButtonRegister() {

    }
}