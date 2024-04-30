package components.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
  title: String?,
  onLeftPressed: (() -> Unit)? = null,
  modifier: Modifier = Modifier,
  navigationIcon: ImageVector = Icons.Filled.KeyboardArrowLeft,
  actions: @Composable RowScope.() -> Unit = {},
) {
  val navigator = LocalNavigator.currentOrThrow

  CenterAlignedTopAppBar(
    modifier = modifier,
    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
    title = {
      Text(
        text = title ?: "",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary,
      )
    },
    navigationIcon = {
      IconButton(onClick = { onLeftPressed?.invoke() ?: navigator.pop() }) {
        Icon(
          imageVector = navigationIcon,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onPrimary,
        )
      }
    },
    actions = actions,
  )
}
