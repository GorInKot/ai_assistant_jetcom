package com.example.ai_assistant.Auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: (jwt: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var isRegister by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isRegister) "Регистрация" else "Вход",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true
        )

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        if (isRegister) {
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Полное имя (опционально)") },
                singleLine = true
            )
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (isRegister) {
                viewModel.register(email, password, fullName.ifBlank { null })
            } else {
                viewModel.login(email, password)
            }
        }) {
            Text(text = if (isRegister) "Зарегистрироваться" else "Войти")
        }

        TextButton(onClick = { isRegister = !isRegister }) {
            Text(text = if (isRegister) "Уже есть аккаунт? Войти" else "Нет аккаунта? Зарегистрироваться")
        }

        Spacer(Modifier.height(16.dp))
        when (state) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Error -> Text(
                (state as AuthState.Error).message,
                color = MaterialTheme.colorScheme.error
            )
            is AuthState.Success -> {
                LaunchedEffect(state) {
                    val token = (state as AuthState.Success).token // <- берем токен из state
                    onLoginSuccess(token) // вызываем callback для сохранения токена
                }
            }
            else -> {}
        }

    }
}

