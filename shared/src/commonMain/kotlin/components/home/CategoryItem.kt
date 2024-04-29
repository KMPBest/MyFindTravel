package components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import configs.uis.Orange_alpha_50
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import screens.home.CategoryData

@OptIn(ExperimentalResourceApi::class)
@Composable
public fun CategoryItem(
  modifier: Modifier = Modifier,
  categoryData: CategoryData,
  onClick: () -> Unit,
) {
  Column(
    modifier =
      modifier
        .border(1.dp, Orange_alpha_50, RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.secondaryContainer)
        .clickable { onClick() }
        .padding(vertical = 12.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Image(
      painter = painterResource(categoryData.iconRes),
      modifier = Modifier.size(50.dp),
      contentDescription = null,
      contentScale = ContentScale.Fit,
    )
    Spacer(modifier = Modifier.weight(1f))
    Text(
      text = categoryData.title,
      style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
    )
  }
}
