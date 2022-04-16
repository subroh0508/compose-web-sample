import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.renderComposable

object AppStyleSheet : StyleSheet() {
    const val ELEMENT_ID = "root"

    init {
        "html, body" style {
            height(100.percent)
            margin(0.px)
        }
        "style" style {
            margin(0.px)
        }
        "div#$ELEMENT_ID" style {
            height(100.percent)
        }
    }

    val container by style {
        display(DisplayStyle.Flex)
        height(100.percent)
    }
}

fun main() {
    renderComposable(rootElementId = AppStyleSheet.ELEMENT_ID) {
        Style(AppStyleSheet)

        App(window.location.pathname, window.location.search) { root ->
            Div({ classes(AppStyleSheet.container) }) {
                Menu(root) { page ->
                    when (page) {
                        is RootComponent.Page.Home -> Home()
                        is RootComponent.Page.Content -> Contents(page)
                        is RootComponent.Page.About -> About()
                    }
                }
            }
        }
    }
}