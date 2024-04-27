package navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import configs.uis.Strings
import data.repository.UserRepository
import org.koin.compose.koinInject
import screens.about.AboutScreen
import screens.auth.signin.SigInScreen
import screens.home.HomeScreen
import screens.home.HomeUiStateHolder
import screens.more.MoreScreen
import screens.profile.ProfileScreen
import screens.topFlights.TopFlightScreen
import screens.topFlights.TopFlightsUiStateHolder
import screens.webview.WebViewScreen
import utils.getUiStateHolder

interface IScreens {
    object Signin: Screen, TopLevelIScreens {
        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow

//            val userRepository = koinInject<UserRepository>()
//            val currentUserState by userRepository

            SigInScreen(
                onNavigatePrivacyPolicy = {
                    navigator.navigate(
                        WebView(
                            url = "url",
                            title = "Privacy policy"
                        )
                    )
                },
                onNavigateTermsConditions = {
                    navigator.navigate(
                        WebView(
                            url = "url",
                            title = "Terms conditions"
                        )
                    )
                }
            )
        }
    }

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
            val navigator = LocalNavigator.currentOrThrow
            MoreScreen(
                onNavigateAboutUs = {
                    navigator.replace(About)
                },
                onNavigatePrivacy = {
                    navigator.navigate(
                        WebView(
                            url = Strings.url_privacy_policy,
                            title = Strings.privacy_policy
                        )
                    )
                },
                onNavigateTerms = {
                    navigator.navigate(
                        WebView(
                            url = Strings.url_terms_conditions,
                            title = Strings.terms_conditions
                        )
                    )
                }
            )
        }

        override fun getTitle(): String {
            return "More"
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

        override fun getTitle(): String {
            return "Top 5 Flights"
        }
    }

    object About: Screen, IScreens {
        @Composable
        override fun Content() {
            AboutScreen()
        }

        override fun getTitle(): String {
            return "About"
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