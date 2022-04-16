import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun Contents(page: RootComponent.Page.Content) {
    Style(ContentsStyle)

    Div({
        classes(
            when (page.color) {
                "red" -> ContentsStyle.red
                "blue" -> ContentsStyle.blue
                "green" -> ContentsStyle.green
                else -> ContentsStyle.common
            }
        )
    }) {
        Text(
            buildString {
                append("Content: ")
                append(page.color?.uppercase() ?: "DEFAULT")
            },
        )
    }
}

private object ContentsStyle : StyleSheet() {
    val common by style {
        color(Color.black)
    }

    val red by style {
        color(Color.red)
    }

    val blue by style {
        color(Color.blue)
    }

    val green by style {
        color(Color.green)
    }
}
