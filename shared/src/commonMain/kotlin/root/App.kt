package root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import configs.uis.MyAppTheme

@Composable
fun App(modifier: Modifier = Modifier) {
  MyAppTheme {
    Box(
      modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    ) {
//            var rootAppUiState by remember { mutableStateOf(`AppUiState.kt`()) }
//            val userPreferences
      NavigationContainer(false)
    }
  }
}

@Composable
private fun NavigationContainer(isOnBoardShown: Boolean) {
  val startScreen =
    INavigatioinContainer.Main
  NavigationContainer(startScreen)
}
