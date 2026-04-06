package com.example.secretmessageapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EncoderDecoderApp() {
    var inputEncode by remember { mutableStateOf("") }
    var encodedResult by remember { mutableStateOf("") }
    var inputDecode by remember { mutableStateOf("") }
    var decodedResult by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        // Background image (very faint)
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f)
        )

        // Dark overlay so everything on top is easy to read
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xBB000000))
        )

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Secret Message App",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Encode or decode your secret messages",
                fontSize = 13.sp,
                color = Color(0xFFAAAAAA),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Encode section
            OutlinedTextField(
                value = inputEncode,
                onValueChange = { inputEncode = it },
                label = { Text("Enter text to encode", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                onClick = { encodedResult = encode(inputEncode) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF00BCD4)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Encode", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1A1A1A))
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = if (encodedResult.isEmpty()) "Result appears here..." else encodedResult,
                        fontSize = 15.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Decode section
            OutlinedTextField(
                value = inputDecode,
                onValueChange = { inputDecode = it },
                label = { Text("Enter text to decode", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.White),
            )

            Button(
                onClick = { decodedResult = decode(inputDecode) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE040FB)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Decode", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1A1A1A))
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = if (decodedResult.isEmpty()) "Result appears here..." else decodedResult,
                        fontSize = 15.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

fun encode(input: String): String {
    return input.map { char ->
        (char.code + 2).toChar()
    }.joinToString("")
}

fun decode(input: String): String {
    return input.map { char ->
        (char.code - 2).toChar()
    }.joinToString("")
}
