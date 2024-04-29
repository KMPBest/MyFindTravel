package screens.more

import configs.uis.Strings

data class MoreScreenUiState(
  val items: List<String> =
    listOf(
      Strings.feedback,
      Strings.share_app,
      Strings.rate_app,
      Strings.about_us,
      Strings.privacy_policy,
      Strings.terms_conditions,
    ),
)
