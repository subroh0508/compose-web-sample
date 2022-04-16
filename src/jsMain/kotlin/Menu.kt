@file:Suppress("FunctionName")

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.Element

private object MenuStyleSheet : StyleSheet() {
    val container by style {
        height(100.percent)
        width(200.px)
        border {
            style(LineStyle.Solid)
            color(Color.black)
        }
        borderWidth(0.px, 1.px, 0.px, 0.px)
    }

    val list by style {
        padding(0.px)
        marginLeft(16.px)
        listStyle("none")

        "li" {
            padding(8.px, 0.px)
        }
    }
}

@Composable
fun Menu(
    content: @Composable DOMScope<Element>.() -> Unit,
) {
    Style(MenuStyleSheet)

    Div({ classes(MenuStyleSheet.container) }) {
        Ul({ classes(MenuStyleSheet.list) }) {
            Li {
                Text("Home")
            }
            Li {
                Text("Contents")
            }
            Li {
                Text("About")
            }
        }
    }
}
