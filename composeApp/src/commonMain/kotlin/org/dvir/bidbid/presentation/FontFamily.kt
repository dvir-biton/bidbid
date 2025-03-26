package org.dvir.bidbid.presentation

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import bidbid.composeapp.generated.resources.Res
import bidbid.composeapp.generated.resources.montserrat_black
import bidbid.composeapp.generated.resources.montserrat_blackitalic
import bidbid.composeapp.generated.resources.montserrat_bold
import bidbid.composeapp.generated.resources.montserrat_bolditalic
import bidbid.composeapp.generated.resources.montserrat_extrabold
import bidbid.composeapp.generated.resources.montserrat_extrabolditalic
import bidbid.composeapp.generated.resources.montserrat_extralight
import bidbid.composeapp.generated.resources.montserrat_extralightitalic
import bidbid.composeapp.generated.resources.montserrat_italic
import bidbid.composeapp.generated.resources.montserrat_light
import bidbid.composeapp.generated.resources.montserrat_lightitalic
import bidbid.composeapp.generated.resources.montserrat_medium
import bidbid.composeapp.generated.resources.montserrat_mediumitalic
import bidbid.composeapp.generated.resources.montserrat_regular
import bidbid.composeapp.generated.resources.montserrat_semibold
import bidbid.composeapp.generated.resources.montserrat_semibolditalic
import bidbid.composeapp.generated.resources.montserrat_thin
import bidbid.composeapp.generated.resources.montserrat_thinitalic
import org.jetbrains.compose.resources.Font

@Composable
fun fontFamily() = FontFamily(
    Font(
        Res.font.montserrat_black,
        FontWeight.Black
    ),
    Font(
        Res.font.montserrat_blackitalic,
        FontWeight.Black, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_bold,
        FontWeight.Bold
    ),
    Font(
        Res.font.montserrat_bolditalic,
        FontWeight.Bold, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_extrabold,
        FontWeight.Bold
    ),
    Font(
        Res.font.montserrat_extrabolditalic,
        FontWeight.Bold, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_extralight,
        FontWeight.ExtraLight
    ),
    Font(
        Res.font.montserrat_extralightitalic,
        FontWeight.ExtraLight, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_italic,
        FontWeight.Normal, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_light,
        FontWeight.Light
    ),
    Font(
        Res.font.montserrat_lightitalic,
        FontWeight.Light, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_medium,
        FontWeight.Medium
    ),
    Font(
        Res.font.montserrat_mediumitalic,
        FontWeight.Medium, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_regular,
        FontWeight.Normal
    ),
    Font(
        Res.font.montserrat_semibold,
        FontWeight.SemiBold
    ),
    Font(
        Res.font.montserrat_semibolditalic,
        FontWeight.SemiBold, style = FontStyle.Italic
    ),
    Font(
        Res.font.montserrat_thin,
        FontWeight.Thin
    ),
    Font(
        Res.font.montserrat_thinitalic,
        FontWeight.Thin, style = FontStyle.Italic
    )
)

@Composable
fun MontserratTypography() = Typography().run {
    copy(
        h1 = h1.copy(fontFamily = fontFamily()),
        h2 = h2.copy(fontFamily = fontFamily()),
        h3 = h3.copy(fontFamily = fontFamily()),
        h4 = h4.copy(fontFamily = fontFamily()),
        h5 = h5.copy(fontFamily = fontFamily()),
        h6 = h6.copy(fontFamily = fontFamily()),
        subtitle1 = subtitle1.copy(fontFamily = fontFamily()),
        subtitle2 = subtitle2.copy(fontFamily = fontFamily()),
        body1 = body1.copy(fontFamily = fontFamily()),
        body2 = body2.copy(fontFamily = fontFamily()),
        button = button.copy(fontFamily = fontFamily()),
        caption = caption.copy(fontFamily = fontFamily()),
        overline = overline.copy(fontFamily = fontFamily())
    )
}
