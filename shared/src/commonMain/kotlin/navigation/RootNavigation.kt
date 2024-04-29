package navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import components.common.MyAppToolbar

interface TopLevelIScreens : IScreens {
  companion object {
    fun getStartScreen(): TopLevelIScreens = IScreens.Home
  }
}

fun IScreens.isTopLevelScreen() = this is TopLevelIScreens

fun Navigator.navigate(name: IScreens) {
  val nameScreen = name as Screen
  when {
    name.isTopLevelScreen() && name == TopLevelIScreens.getStartScreen() ->
      this.replaceAll(nameScreen)

    name.isTopLevelScreen() ->
      with(this) {
        popUntilRoot()
        push(nameScreen)
      }

    else -> this.push(nameScreen)
  }
}

@Composable
fun RootNavigation(
  modifier: Modifier = Modifier.fillMaxSize(),
  uiStateHolder: RootUiStateHolder,
) {
  val startScreen = TopLevelIScreens.getStartScreen() as Screen
  Navigator(
    screen = startScreen,
  ) { navigator ->
    val currentScreen = navigator.lastItem
    val currentIScreen = currentScreen as IScreens

    Column(modifier = modifier.fillMaxSize()) {
      val isToolbarInvisible =
        (currentScreen == IScreens.Home) ||
          (currentScreen == IScreens.Signin)
      AnimatedVisibility(!isToolbarInvisible) {
        MyAppToolbar(
          title = currentScreen.getTitle(),
          onNavigationIconClick = { navigator.pop() },
        )
      }
      Box(modifier = Modifier.weight(1f)) {
        AnimatedTransition(navigator)
      }

      if (currentIScreen.isTopLevelScreen()) {
        BottomNavigation(
          selectedNavItem = currentIScreen.asBottomNavItem(),
          onNavigationItemSeleted = {
            navigator.navigate(it.asTopLevel())
          },
        )
      }
    }
  }
}

@Composable
private fun AnimatedTransition(navigator: Navigator) {
  AnimatedContent(
    targetState = navigator.lastItem,
    transitionSpec = {
      val (initialScale, targetScale) =
        when (navigator.lastEvent) {
          StackEvent.Pop -> 1f to 0.85f
          else -> 0.85f to 1f
        }

      val stiffness = Spring.StiffnessMediumLow
      val enterTransition =
        fadeIn(tween(easing = EaseIn)) +
          scaleIn(
            spring(stiffness = stiffness),
            initialScale = initialScale,
          )

      val exitTransition =
        fadeOut(spring(stiffness = stiffness)) +
          scaleOut(
            tween(easing = EaseOut),
            targetScale = targetScale,
          )

      enterTransition togetherWith exitTransition
    },
  ) { currentScreen ->
    navigator.saveableState("transition", currentScreen) {
      currentScreen.Content()
    }
  }
}
