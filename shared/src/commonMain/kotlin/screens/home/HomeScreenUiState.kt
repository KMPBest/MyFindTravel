package screens.home

import domain.model.FlightInfo

data class HomeScreenUiState(
  val categories: List<CategoryData> =
    listOf(
      CategoryData(
        title = "Flight",
        iconRes = "drawable/ic_category_flight.xml",
        url = "https://appfindtravelnow.blogspot.com/p/flight.html",
      ),
      CategoryData(
        title = "Hotel",
        iconRes = "drawable/ic_category_hotel.xml",
        url = "https://appfindtravelnow.blogspot.com/p/hotel.html",
      ),
      CategoryData(
        title = "Car",
        iconRes = "drawable/ic_category_car.xml",
        url = "https://appfindtravelnow.blogspot.com/p/car.html",
      ),
      CategoryData(
        title = "Taxi",
        iconRes = "drawable/ic_category_taxi.xml",
        url = "https://appfindtravelnow.blogspot.com/p/taxi.html",
      ),
      CategoryData(
        title = "Bike",
        iconRes = "drawable/ic_category_bike.xml",
        url = "https://appfindtravelnow.blogspot.com/p/bike.html",
      ),
      CategoryData(
        title = "eSim",
        iconRes = "drawable/ic_category_sim.xml",
        url = "https://appfindtravelnow.blogspot.com/p/esim.html",
      ),
    ),
  val topFlightInfoList: List<FlightInfo> = listOf(),
)

data class CategoryData(
  val title: String = "",
  val iconRes: String = "",
  val url: String = "",
)
