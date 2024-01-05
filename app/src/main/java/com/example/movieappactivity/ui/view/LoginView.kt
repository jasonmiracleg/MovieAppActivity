package com.example.movieappactivity.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappactivity.R
import com.example.movieappactivity.data.DataStoreManager
import com.example.movieappactivity.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(
    loginViewModel: LoginViewModel,
    navController: NavController,
    dataStore: DataStoreManager
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var isEmailValid by rememberSaveable { mutableStateOf(true) }
    var isPasswordValid by rememberSaveable { mutableStateOf(true) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomEmailField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isEmailValid = isEmailValid,
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                CustomPasswordField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isPassInvalid = isPasswordValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Button(
                    onClick = {
                        isEmailValid = isValidEmail(email)
                        isPasswordValid = isValidPassword(password)

                        if (isEmailValid && isPasswordValid) {
                            loginViewModel.ButtonLogin(
                                email,
                                password,
                                context,
                                navController,
                                dataStore
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = email.isNotBlank() && password.isNotBlank()
                )
                {
                    Text(text = "Login")
                }
                Button(
                    onClick = {
                        loginViewModel.ButtonRegister()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
                {
                    Text(text = "Register")
                }
            }
        }
    )
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun LoginPreview() {
//    LoginView()
//}