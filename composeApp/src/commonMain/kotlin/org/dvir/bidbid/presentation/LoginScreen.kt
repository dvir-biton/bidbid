package org.dvir.project.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.dvir.bidbid.presentation.components.AuthTextField

@Composable
fun LoginScreen() {
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var usernameText by remember { mutableStateOf("") }
    var isUsernameFocused by remember { mutableStateOf(false) }

    var creditCardText by remember { mutableStateOf("") }
    var isCreditCardFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { focusManager.clearFocus() }
            )
            .background(Color(0xFF212121))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "bidbid",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Meet the new way to bid",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraLight
            )
        }

        Column {
            AuthTextField(
                value = usernameText,
                onValueChange = { usernameText = it },
                hint = "Username",
                isHintVisible = usernameText.isEmpty() && !isUsernameFocused,
                icon = Icons.Default.Person,
                keyboardOptions = KeyboardOptions.Default,
                onFocusChange = {
                    isUsernameFocused = it.isFocused
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AuthTextField(
                value = creditCardText,
                onValueChange = { creditCardText = it },
                hint = "Credit card (not your real one)",
                isHintVisible = creditCardText.isEmpty() && !isCreditCardFocused,
                icon = Icons.Default.Info,
                keyboardOptions = KeyboardOptions.Default,
                onFocusChange = {
                    isCreditCardFocused = it.isFocused
                }
            )
        }

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .height(48.dp)
                .fillMaxWidth()
                .background(Color(0xFF486EB4))
                .clickable {
                    keyboardController?.hide()
                    // TODO: navigate and send
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Join",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}