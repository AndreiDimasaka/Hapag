package com.example.hapag.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hapag.R
import com.example.hapag.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextItemRow(
    hint: String,
    item: String,
    onTextChange: (String) -> Unit,
    onOptionsClick: () -> Unit,
    modifier: Modifier = Modifier,
    reorderHandlerModifier: Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colorScheme.background,
                shape = AppTheme.shape.container)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Icon(
            painter = painterResource( R.drawable.baseline_dehaze),
            contentDescription = null,
            tint = AppTheme.colorScheme.secondary,
            modifier = reorderHandlerModifier
        )
        Spacer(Modifier.width(6.dp))
        TextField(
            shape = AppTheme.shape.textbox,
            value = item,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent),
            colors = TextFieldDefaults.colors(



                focusedContainerColor = AppTheme.colorScheme.tertiary,
                unfocusedContainerColor = AppTheme.colorScheme.tertiary,
                disabledContainerColor = AppTheme.colorScheme.tertiary,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent, // Remove underline indicator
                unfocusedIndicatorColor = Color.Transparent, // Remove underline indicator
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            textStyle = AppTheme.typography.bodyMedium,
            placeholder = {
                Text (
                    text = hint,
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colorScheme.onBackground.copy(alpha = 0.6f)) }
        )
        IconButton(onClick = onOptionsClick) {
            Icon(
                painter = painterResource(R.drawable.close),
                contentDescription = "Options",
                tint = AppTheme.colorScheme.secondary
            )
        }

    }
}
