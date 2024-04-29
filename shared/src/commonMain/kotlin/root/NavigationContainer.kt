package root

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import navigation.RootNavigation
import navigation.RootUiStateHolder
import utils.getUiStateHolder

interface INavigatioinContainer {
  object Main : Screen, INavigatioinContainer {
    @Composable
    override fun Content() {
      val rootScreenMainUiStateHolder = getUiStateHolder<RootUiStateHolder>()
      RootNavigation(uiStateHolder = rootScreenMainUiStateHolder)
    }
  }
}

@Composable
fun NavigationContainer(startScreen: INavigatioinContainer) {
  Navigator(screen = startScreen as Screen)
}
