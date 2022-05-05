import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun About() {
    SideEffect {
        val button = document.querySelector(".mdc-button")
        attachRippleTo(button)
    }

    Div { Text("About") }
    Button({
        classes("mdc-button", "mdc-button--raised")
    }) {
        Span({ classes("mdc-button__ripple") })
        Span({ classes("mdc-button__label") }) {
            Text("Button")
        }
    }
}
