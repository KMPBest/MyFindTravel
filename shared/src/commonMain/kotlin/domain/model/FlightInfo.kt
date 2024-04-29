package domain.model

import utils.extensions.toFormattedDate

data class FlightInfo(
  val date: String = "",
  val priceWithCurrency: String = "",
  val origin: FlightLocation = FlightLocation(),
  val destination: FlightLocation = FlightLocation(),
) {
  fun getUrl(): String {
    val urlArgs = "${origin.iataCode}${date.toFormattedDate("ddMM")}${destination.iataCode}1"
    return "https://search.findtravelnow.com/flights/$urlArgs"
  }
}
