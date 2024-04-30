package screens.webview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.common.Header
import components.common.WebView
import components.common.Wrapper

@Composable
fun WebViewScreen(
  modifier: Modifier = Modifier,
  url: String,
  title: String? = "",
) {
  Wrapper(
    topSafeArea = false,
    bottomSafeArea = true,
  ) {
    Header(
      title = title,
    )
    WebView(url = url, modifier = modifier.fillMaxSize())
  }
}
