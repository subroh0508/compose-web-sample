import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun Menu(
    root: RootComponent,
    content: @Composable (RootComponent.Page) -> Unit,
) {
    val routerState by root.routerState.subscribeAsState()

    Style(MenuStyleSheet)

    Div({ classes(MenuStyleSheet.container) }) {
        Ul({ classes(MenuStyleSheet.list) }) {
            Li(
                attrs = {
                    onClick { root.toHome() }
                },
            ) {
                Text("Home")
            }
            Li(
                attrs = {
                    onClick { root.toContents() }
                },
            ) {
                Text("Contents")
            }
            Li(
                attrs = {
                    onClick { root.toAbout() }
                },
            ) {
                Text("About")
            }
        }
    }

    Div({ classes(MenuStyleSheet.main) }) {
        content(routerState.activeChild.instance)
    }
}

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

    val main by style {
        padding(16.px)
    }
}
