package screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.common.Wrapper
import components.home.CardViewBanner
import components.home.CategoryItem
import components.home.FlightInfoItem
import domain.model.FlightInfo
import utils.asState
import utils.logging.AppLogger

@Composable
fun HomeScreen(
  uiStateHolder: HomeUiStateHolder,
  onNavigateTop5Flights: () -> Unit,
  onNavigateCategory: (CategoryData) -> Unit,
  onNavigateFlightInfo: (FlightInfo) -> Unit,
) {
  val uiState by uiStateHolder.uiState.asState()
  HomeScreen(
    uiState = uiState,
    onNavigateTop5Flights = onNavigateTop5Flights,
    onNavigateCategory = onNavigateCategory,
    onNavigateFlightInfo = onNavigateFlightInfo,
  )
}

@Composable
fun HomeScreen(
  uiState: HomeScreenUiState,
  onNavigateTop5Flights: () -> Unit,
  onNavigateCategory: (CategoryData) -> Unit,
  onNavigateFlightInfo: (FlightInfo) -> Unit,
) {
  val systemBarPaddingValues = WindowInsets.systemBars.asPaddingValues()
  Wrapper(
    modifier =
      Modifier.padding(
        horizontal = 30.dp,
      ),
  ) {
    LazyColumn {
      item { Spacer(modifier = Modifier.height(36.dp)) }

      item {
        Row(modifier = Modifier.fillMaxWidth()) {
          Text(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = "Find your travel plans with us",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start,
            color = Color.Black,
          )
          Spacer(modifier = Modifier.weight(1f))
        }
      }

      item {
        CardViewBanner()
      }

      item {
        CategoryTitle()
      }

      item {
        CategoriesSection(
          modifier = Modifier.padding(top = 18.dp),
          categories = uiState.categories,
          onClickCategory = { categoryData ->
            AppLogger.d(categoryData.toString())
          },
        )
      }

      top5FlightsSection(
        modifier = Modifier.padding(top = 18.dp),
        topFlightInfoList = uiState.topFlightInfoList,
        onClickViewAll = onNavigateTop5Flights,
        onNavigateFlightInfo = onNavigateFlightInfo,
      )
    }
  }
}

@Composable
private fun CategoryTitle(modifier: Modifier = Modifier) {
  Text(
    text = "Our Categories",
    style = MaterialTheme.typography.titleSmall,
    modifier = Modifier.padding(top = 10.dp),
    color = Color.Black,
  )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategoriesSection(
  modifier: Modifier,
  categories: List<CategoryData>,
  onClickCategory: (CategoryData) -> Unit,
) {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalArrangement = Arrangement.spacedBy(18.dp),
  ) {
    categories.forEach {
      CategoryItem(
        categoryData = it,
        modifier =
          modifier.sizeIn(
            minWidth = 100.dp,
            minHeight = 100.dp,
            maxWidth = 100.dp,
            maxHeight = 100.dp,
          ),
        onClick = {
          onClickCategory(it)
        },
      )
    }
  }
}

fun LazyListScope.top5FlightsSection(
  modifier: Modifier = Modifier,
  topFlightInfoList: List<FlightInfo>,
  onClickViewAll: () -> Unit,
  onNavigateFlightInfo: (FlightInfo) -> Unit,
) {
  item {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
      Text(
        text = "Top 5 Flights",
        style = MaterialTheme.typography.titleSmall,
        color = Color.Black,
      )
      Spacer(modifier = Modifier.weight(1f))
      TextButton(
        onClick = {
          onClickViewAll()
        },
      ) {
        Text(
          text = "View all",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.secondary,
        )
      }
    }
  }

  items(topFlightInfoList) {
    FlightInfoItem(
      modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
      flightInfo = it,
      onClickItem = {
        onNavigateFlightInfo(it)
      },
    )
  }
}
