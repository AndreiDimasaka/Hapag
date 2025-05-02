package com.example.hapag.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.text.TextStyle

data class AppColorScheme(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
)

data class AppTypography(
    val titleLarge : TextStyle,
    val titleMedium : TextStyle,
    val headlineMedium : TextStyle,
    val bodyLarge : TextStyle,
    val bodyMedium : TextStyle,
    val bodySmall : TextStyle,
    val labelLarge : TextStyle,
    val labelMedium : TextStyle,
    val labelSmall : TextStyle,
)

data class AppShape(
    val container: Shape,
    val button: Shape,
    val textbox: Shape,
)

data class AppSize(
    val small: Dp,
    val medium: Dp,
    val normal: Dp,
    val large: Dp,

)

val LocalAppColorScheme =  staticCompositionLocalOf {
    AppColorScheme(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        tertiary = Color.Unspecified,
        onTertiary = Color.Unspecified,
    )
}
val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        headlineMedium = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodySmall = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelSmall = TextStyle.Default

    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        button = RectangleShape,
        textbox = RectangleShape,
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        small = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        large = Dp.Unspecified)
}
