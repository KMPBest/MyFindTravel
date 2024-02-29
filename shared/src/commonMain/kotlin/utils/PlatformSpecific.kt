package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.flow.StateFlow

@Composable
expect fun font(res: String, weight: FontWeight, style: FontStyle): Font

@Composable
expect fun <T> StateFlow<T>.asState(): State<T>

interface AppVersion {
    fun code(): String
    fun name(): String
}