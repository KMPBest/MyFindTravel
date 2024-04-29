package screens.topFlights

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.common.MyAppCircularProgressIndicator
import components.home.FlightInfoItem
import configs.uis.Alabaster
import configs.uis.Orange_alpha_12
import domain.model.FlightInfo
import domain.model.FlightLocation
import domain.model.FlightSort
import utils.asState

@Composable
fun TopFlightScreen(
  uiStateHolder: TopFlightsUiStateHolder,
  onNavigateFlightInfo: (FlightInfo) -> Unit,
) {
  val uiState by uiStateHolder.uiState.asState()
  TopFlightScreen(
    uiState = uiState,
    onSelectSort = uiStateHolder::onSelectSort,
    onNavigateFlightInfo = onNavigateFlightInfo,
  )
}

@Composable
fun TopFlightScreen(
  uiState: TopFlightsUiState,
  onSelectSort: (FlightSort) -> Unit,
  onNavigateFlightInfo: (FlightInfo) -> Unit,
) {
  val systemBarPaddingValues = WindowInsets.systemBars.asPaddingValues()
  Column(
    modifier =
      Modifier.fillMaxSize()
        .padding(
          top = systemBarPaddingValues.calculateTopPadding(),
        ),
  ) {
    SortByDropDown(
      modifier = Modifier.padding(top = 20.dp),
      sortBy = uiState.sortBy,
      onSelectSort = onSelectSort,
    )
    Box(modifier = Modifier.fillMaxSize().padding(top = 12.dp)) {
      if (uiState.isLoading) {
        MyAppCircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
      } else {
        Column(modifier = Modifier.fillMaxSize()) {
          FLightsListUI(
            modifier =
              Modifier.weight(1f).padding(
                start = 30.dp,
                end = 30.dp,
              )
                .padding(vertical = 10.dp),
            topFlightInfoList = uiState.flights,
            onNavigateFlightInfo = onNavigateFlightInfo,
          )

          BottomInfoSection(
            origin = uiState.origin,
            modifier = Modifier.fillMaxWidth(),
            lastUpdateDate = uiState.lastUpdateDate,
            nextUpdateInDate = uiState.nextUpdateInDays,
          )
        }
      }
    }
  }
}

@Composable
private fun SortByDropDown(
  modifier: Modifier,
  sortBy: FlightSort? = null,
  onSelectSort: (FlightSort) -> Unit,
) {
  val shape = RoundedCornerShape(5.dp)
  var isExpanded by remember { mutableStateOf(false) }
  Row(
    modifier =
      modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .clip(shape)
        .border(width = 1.dp, color = Alabaster, shape = shape)
        .background(color = Color(0xFFFFFFFF))
        .clickable {
          isExpanded = !isExpanded
        }
        .padding(start = 13.dp, top = 8.dp, bottom = 8.dp, end = 4.dp),
  ) {
    Text(
      modifier = Modifier.padding(end = 44.dp),
      text =
        when (sortBy) {
          FlightSort.BY_DATE -> "Date"
          FlightSort.BY_PRICE -> "Price"
          null -> "Sort by"
        },
      style = MaterialTheme.typography.bodySmall,
      color = Color.Black,
    )

    Icon(
      imageVector = Icons.Default.ArrowDropDown,
      tint = Color.Black,
      contentDescription = null,
    )

    DropdownMenu(
      expanded = isExpanded,
      onDismissRequest = { isExpanded = false },
      modifier = Modifier.background(Color.White),
    ) {
      DropdownMenuItem(
        text = {
          Text(
            modifier = Modifier.padding(end = 44.dp),
            text = "Price",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
          )
        },
        onClick = {
          isExpanded = false
          onSelectSort(FlightSort.BY_PRICE)
        },
      )
      Divider(color = Alabaster)
      DropdownMenuItem(
        text = {
          Text(
            modifier = Modifier.padding(end = 44.dp),
            text = "Date",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
          )
        },
        onClick = {
          isExpanded = false
          onSelectSort(FlightSort.BY_DATE)
        },
      )
    }
  }
}

@Composable
private fun FLightsListUI(
  modifier: Modifier = Modifier,
  topFlightInfoList: List<FlightInfo>,
  onNavigateFlightInfo: (FlightInfo) -> Unit,
) {
  LazyColumn(modifier = modifier) {
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
}

@Composable
private fun BottomInfoSection(
  origin: FlightLocation,
  modifier: Modifier = Modifier,
  lastUpdateDate: String = "",
  nextUpdateInDate: Int = 0,
) {
  Column(
    modifier =
      modifier
        .background(Orange_alpha_12)
        .padding(vertical = 12.dp, horizontal = 24.dp),
  ) {
    Text(
      text =
        buildAnnotatedString {
          append("Origin: ")
          withStyle(
            style = SpanStyle(fontWeight = FontWeight.SemiBold),
          ) {
            append("${origin.city}, ${origin.country}(${origin.iataCode})")
          }
        },
      style =
        MaterialTheme.typography.bodySmall.copy(
          fontSize = 13.sp,
          fontWeight = FontWeight.Medium,
        ),
      color = Color.Black,
    )

    Row(
      modifier = Modifier.padding(top = 3.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Text(
        modifier = Modifier.padding(end = 8.dp),
        text =
          buildAnnotatedString {
            append("Last update: ")
            withStyle(
              style = SpanStyle(fontWeight = FontWeight.SemiBold),
            ) {
              append(lastUpdateDate)
            }
          },
        style =
          MaterialTheme.typography.bodySmall.copy(
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
          ),
        color = Color.Black,
      )

      Text(
        text =
          buildAnnotatedString {
            append("Next update: ")
            withStyle(
              style = SpanStyle(fontWeight = FontWeight.SemiBold),
            ) {
              val textDaysLater =
                if (nextUpdateInDate > 1) {
                  "Days later"
                } else {
                  "Day later"
                }
              append("$lastUpdateDate $textDaysLater")
            }
          },
        style =
          MaterialTheme.typography.bodySmall.copy(
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
          ),
        color = Color.Black,
        textAlign = TextAlign.End,
      )
    }
  }
}
