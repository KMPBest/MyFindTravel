package data.source.remote.response

import domain.mapper.DomainMapper
import domain.model.FlightLocation
import domain.model.Top5Flights
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopFlightPricesResponse(
  @SerialName("flights") val flights: List<FlightInfoResponse> = emptyList(),
  @SerialName("lastUpdateDate") val lastUpdateDate: String = "",
  @SerialName("nextUpdateDate") val nextUpdateDate: String = "",
) : DomainMapper<Top5Flights> {
  override fun mapToDomainModel(): Top5Flights {
    return Top5Flights(
      flights = flights.map { it.mapToDomainModel() },
      origin =
        flights.firstOrNull()?.let { firstFlight ->
          FlightLocation(
            city = firstFlight.originCity,
            country = firstFlight.originCountry,
            iataCode = firstFlight.originCode,
          )
        }
          ?: FlightLocation.getDefault(),
      lastUpdateDate = lastUpdateDate,
      nextUpdateDate = nextUpdateDate,
    )
  }
}
