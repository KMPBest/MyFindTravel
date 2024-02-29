package screens.more

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MoreScreen(
) {
    val systemBarPaddingValues = WindowInsets.systemBars.asPaddingValues()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = systemBarPaddingValues.calculateTopPadding()
            )
    ) {
        item { Text(text = "MoreScreen") }
    }

}