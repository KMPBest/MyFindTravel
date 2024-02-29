package navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import screens.about.AboutScreen
import screens.home.HomeScreen
import screens.home.HomeUiStateHolder
import screens.more.MoreScreen
import screens.profile.ProfileScreen
import screens.topFlights.TopFlightScreen
import screens.topFlights.TopFlightsUiStateHolder
import screens.webview.WebViewScreen
import utils.getUiStateHolder

interface IScreens {
    object  Home: Screen, TopLevelIScreens {
        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow
            val uiStateHolder = getUiStateHolder<HomeUiStateHolder>()
            HomeScreen(
               uiStateHolder =  uiStateHolder,
                onNavigateTop5Flights = {
                    navigator.navigate(TopFlight)
                },
                onNavigateCategory = { categoryData ->
                    navigator.navigate(
                        WebView(
                        title = categoryData.title,
                            url = categoryData.url
                        )
                    )
                },
                onNavigateFlightInfo = { flightInfo ->
                    navigator.navigate(
                        WebView(
                            title = "Category flight",
                            url = flightInfo.getUrl()
                        )
                    )
                }
            )
        }
    }

    object More: Screen, TopLevelIScreens {
        @Composable
        override fun Content() {
            MoreScreen()
        }
    }

    object Profile: Screen, TopLevelIScreens {
        @Composable
        override fun Content() {
            ProfileScreen()
        }
    }

    object TopFlight: Screen, TopLevelIScreens {
        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow
            val uiStateHolder = getUiStateHolder<TopFlightsUiStateHolder>()
            TopFlightScreen(
                uiStateHolder = uiStateHolder,
                onNavigateFlightInfo = { flightInfo ->
                    navigator.navigate(
                        WebView(
                            title = "Category flight",
                            url = flightInfo.getUrl()
                        )
                    )
                }
            )
        }
    }

    object About: Screen, TopLevelIScreens {
        @Composable
        override fun Content() {
            AboutScreen()
        }
    }

    fun getTitle(): String {
        return ""
    }

    data class WebView(val url: String, private val title: String = ""): Screen, IScreens {
        @Composable
        override fun Content() {
            WebViewScreen(url = url)
        }

        override fun getTitle(): String {
            return title
        }
    }
}