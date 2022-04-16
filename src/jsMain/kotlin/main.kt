import org.jetbrains.compose.web.css.*
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
}

fun main() {
    renderComposable(rootElementId = AppStyleSheet.ELEMENT_ID) {
        Style(AppStyleSheet)

        Menu {  }
    }
}