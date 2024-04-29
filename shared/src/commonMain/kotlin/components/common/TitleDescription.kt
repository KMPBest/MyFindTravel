package components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleDescription(
  modifier: Modifier = Modifier,
  title: String,
  description: String,
) {
  Column(modifier = modifier) {
    Text(
      text = title,
      style = MaterialTheme.typography.bodySmall,
      textAlign = TextAlign.Start,
      color = Color.Black,
    )

    Text(
      text = description,
      style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
      textAlign = TextAlign.Start,
      color = Color.Black,
    )
  }
}
