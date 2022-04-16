import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun Contents() {
    Style(ContentsStyle)

    Div({ classes(ContentsStyle.red) }) { Text("RED") }
}

private object ContentsStyle : StyleSheet() {
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
