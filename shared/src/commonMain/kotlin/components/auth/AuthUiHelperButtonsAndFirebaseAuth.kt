package components.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmk.kmpauth.firebase.apple.AppleButtonUiContainer
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.uihelper.apple.AppleSignInButton
import com.mmk.kmpauth.uihelper.google.GoogleButtonMode
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import dev.gitlive.firebase.auth.FirebaseUser
import domain.model.AuthProvider

@Composable
fun AuthUiHelperButtonsAndFirebaseAuth(
  modifier: Modifier = Modifier,
  authProviders: List<AuthProvider> = AuthProvider.values().asList(),
  onFirebaseResult: (Result<FirebaseUser?>) -> Unit,
) {
  val isExistOnlyOneAuthProvider by remember { mutableStateOf(authProviders.size == 1) }

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(14.dp),
  ) {
    val shape = RoundedCornerShape(8.dp)
    val height = 56.dp
    val textFontSize = 24.sp

    if (authProviders.contains(AuthProvider.GOOGLE)) {
      GoogleButtonUiContainerFirebase(onResult = onFirebaseResult) {
        LaunchedEffect(Unit) { if (isExistOnlyOneAuthProvider) this@GoogleButtonUiContainerFirebase.onClick() }
        GoogleSignInButton(
          modifier = Modifier.fillMaxWidth().height(height),
          text = "Sign in with Google",
          mode = GoogleButtonMode.Light,
          fontSize = textFontSize,
          shape = shape,
        ) { this.onClick() }
      }
    }

    if (authProviders.contains(AuthProvider.APPLE)) {
      AppleButtonUiContainer(onResult = onFirebaseResult) {
        LaunchedEffect(Unit) { if (isExistOnlyOneAuthProvider) this@AppleButtonUiContainer.onClick() }
        AppleSignInButton(
          modifier = Modifier.fillMaxWidth().height(height),
          text = "Sign in with Apple",
          shape = shape,
        ) { this.onClick() }
      }
    }
  }
}
