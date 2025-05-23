package com.example.hapag.composables.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapag.theme.AppTheme

@Composable
fun ThemedTitleTextField(
    modifier: Modifier = Modifier,
    initialValue: String = "",
    onValueChange: (String) -> Unit = {},
    hint: String,
    style: TextStyle = AppTheme.typography.bodyMedium
) {

    var text by remember { mutableStateOf(initialValue) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = hint,
                style = style,
                color = AppTheme.colorScheme.onBackground
            )
        },
        textStyle = AppTheme.typography.bodyMedium.copy(
            color = AppTheme.colorScheme.onBackground
        ),
        colors = TextFieldDefaults.colors(

            focusedContainerColor = AppTheme.colorScheme.tertiary,
            unfocusedContainerColor = AppTheme.colorScheme.tertiary,
            disabledContainerColor = AppTheme.colorScheme.tertiary.copy(alpha = 0.5f),
            errorContainerColor = AppTheme.colorScheme.tertiary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedTextColor = AppTheme.colorScheme.onBackground,
            cursorColor = AppTheme.colorScheme.onBackground,
            unfocusedPlaceholderColor = AppTheme.colorScheme.onBackground.copy(alpha = 0.5f), // Use onSurfaceVariant for a slightly dimmer placeholder
            // --- Leading/Trailing Icon Color ---
            // leadingIconColor = MaterialTheme.colorScheme.onBackground,
            // trailingIconColor = MaterialTheme.colorScheme.onBackground,


        ),
        shape = AppTheme.shape.textbox,
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewTextBoxWithTitle() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThemedTitleTextField(
            hint = "Enter the Name of Your Recipe",
        )
    }
}

