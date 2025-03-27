package org.dvir.bidbid.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.dvir.bidbid.presentation.login.components.CustomTextField

@Composable
fun ChatRoomScreen(
    viewModel: ChatViewModel,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat Room", color = Color.White) },
                backgroundColor = Color(0xFF212121),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        },
        backgroundColor = Color(0xFF212121)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 24.dp),
                reverseLayout = true
            ) {
                items(viewModel.messages.toList().reversed()) { message ->
                    Text(message, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    value = viewModel.currentMessage,
                    onValueChange = { viewModel.onMessageChange(it) },
                    hint = "Type a message...",
                    isHintVisible = viewModel.currentMessage.isBlank(),
                    icon = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions.Default,
                    onFocusChange = {}
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(48.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF486EB4))
                        .clickable {
                            viewModel.onSend()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Send",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}