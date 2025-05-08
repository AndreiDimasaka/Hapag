package com.example.hapag.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hapag.theme.AppTheme

@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) AppTheme.colorScheme.primary else  backgroundColor,
            contentColor = if (isSelected) AppTheme.colorScheme.onPrimary else AppTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(text = text, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelLarge)
    }
}
