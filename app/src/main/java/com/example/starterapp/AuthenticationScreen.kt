package com.example.starterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class AuthenticationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    fun AppNavigation() {
        // The navcon is part of navigation concept of jetpack
        val navController = rememberNavController()
        // set up a navhost to host app's routes  i.e. /home / login
        NavHost(navController = navController, startDestination = "Login") {
            // inside this we then add our composable with path names
            composable("Login") { LoginScreen(navController) }
            composable("Signup") { SignUpScreen(navController) }
            composable ("Forgotpassword") { ForgotPasswordScreen(navController) }

        }
    }

    // composable login
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginScreen(navController: NavController) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordVisible = remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    // logo
                    Image(
                        painter = painterResource(R.drawable.login),
                        contentDescription = "APP logo",
                        modifier = Modifier.size(120.dp),
                        contentScale = ContentScale.Fit
                    )
                    // space
                    Spacer(modifier = Modifier.height(32.dp))
                    // username text field


                    OutlinedTextField(
                        value = username.value,
                        onValueChange = { username.value = it },
                        label = { Text("Username or Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Clickable Text
                    Text(
                        text = "Forgot Password ? ",
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.align(Alignment.End).clickable(onClick = {
                            //   handle reset password functionality here
                        })
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // password
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Gray
                        ),
                        visualTransformation = if (passwordVisible.value) VisualTransformation.None else
                            PasswordVisualTransformation(),

                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisible.value = !passwordVisible.value
                            }) {
                                Icon(
                                    imageVector = if (
                                        passwordVisible.value
                                    ) Icons.Filled.ThumbUp else
                                        Icons.Filled.AccountBox,
                                    contentDescription = ""
                                )
                            }
                        }

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Clickable Text
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.Blue
                        )
                    ) {
                        Text(text = "Log in", fontSize = 16.sp)
                    }
                    // linking element
                    Text(
                        text = "Don't have an account ? Sign up here.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            navController.navigate("Signup")
                        }
                    )

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SignUpScreen(navController: NavController) {
     val username = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordVisible = remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
     ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
              )

            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    // logo
                    Image(
                        painter = painterResource(R.drawable.sign),
                        contentDescription = "APP logo",
                        modifier = Modifier.size(300.dp),
                        contentScale = ContentScale.Fit
                    )
                    // space
                    Spacer(modifier = Modifier.height(32.dp))
                    // username text field
                    OutlinedTextField(
                       value = username.value,
                       onValueChange = { username.value = it },
                        label = { Text("Username or Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.White
                        )
                    )
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text(" Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.White
                        )
                    )
                   Spacer(modifier = Modifier.height(16.dp))
                    // password
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Password") },
                       modifier = Modifier.fillMaxWidth(),
                       singleLine = true,
                       colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = Color.Transparent,
                           unfocusedContainerColor = Color.White
                        ),
                        visualTransformation = if (passwordVisible.value) VisualTransformation.None else
                            PasswordVisualTransformation(),

                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisible.value = !passwordVisible.value
                           }) {
                                Icon(
                                    imageVector = if (
                                       passwordVisible.value
                                  ) Icons.Filled.ThumbUp else
                                       Icons.Filled.AccountBox,
                                        contentDescription = ""
                                )
                           }
                        }

                    )
                    Spacer(modifier = Modifier.height(24.dp))
                                // login button
                                Button(
                                onClick = { },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Sign up", fontSize = 16.sp)
                    }
                    // linking element
                    Text(
                        text = "Already have an account ? Login here.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            navController.navigate("Login")
                        }
                    )
                }
            }
       }

    }
    @Composable
    fun ForgotPasswordScreen(navController: NavController) {
        val password = remember { mutableStateOf("") }
        val passwordVisible = remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,

        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    // logo
                    Image(
                        painter = painterResource(R.drawable.pass),
                        contentDescription = "APP logo",
                        modifier = Modifier.size(300.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    // password
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.White
                        ),
                        visualTransformation = if (passwordVisible.value) VisualTransformation.None else
                            PasswordVisualTransformation(),

                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisible.value = !passwordVisible.value
                            }) {
                                Icon(
                                    imageVector = if (
                                        passwordVisible.value
                                    ) Icons.Filled.ThumbUp else
                                        Icons.Filled.AccountBox,
                                    contentDescription = ""
                                )
                            }
                        }

                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    // login button
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Reset", fontSize = 16.sp)
                    }
                    Text(
                        text = "Login.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            navController.navigate("login")
                        }
                    )

                }
            }
        }
    }
                @Preview(showBackground = true)
                @Composable
                fun LoginScreenPreview() {
                    MaterialTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background

                        ) {
                            LoginScreen(rememberNavController())
                        }
                    }
                }

                @Preview(showBackground = true)
                @Composable
                fun SignUpScreenPreview() {
                    MaterialTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background

                        ) {
                            SignUpScreen(rememberNavController())
                        }
                    }
                }

                @Preview(showBackground = true)
                @Composable
                fun ForgotPasswordScreenPreview(){
                    MaterialTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background

                        ) {
                            ForgotPasswordScreen(rememberNavController())
                        }
                    }

                }


            }






























