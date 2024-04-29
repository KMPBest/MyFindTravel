package screens.more

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.more.MoreItem
import configs.uis.Strings
import org.koin.compose.koinInject
import utils.AppOpenerUtil

@Composable
fun MoreScreen(
  onNavigateAboutUs: () -> Unit,
  onNavigatePrivacy: () -> Unit,
  onNavigateTerms: () -> Unit,
) {
  val uiState by remember { mutableStateOf(MoreScreenUiState()) }
  val appOpenerUtil = koinInject<AppOpenerUtil>()

  val systemBarPaddingValues = WindowInsets.systemBars.asPaddingValues()
  LazyColumn(
    modifier =
      Modifier.fillMaxSize()
        .padding(
          start = 30.dp,
          end = 30.dp,
          top = 40.dp,
        ),
    verticalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    items(uiState.items, key = { it }) {
      MoreItem(
        value = it,
        onClick = {
          when (it) {
            Strings.feedback -> appOpenerUtil.openFeedbackMail()
            Strings.share_app -> appOpenerUtil.shareApp()
            Strings.rate_app -> appOpenerUtil.openStorePage()
            Strings.about_us -> onNavigateAboutUs()
            Strings.privacy_policy -> onNavigatePrivacy()
            Strings.terms_conditions -> onNavigateTerms()
          }
        },
      )
    }
  }
}
