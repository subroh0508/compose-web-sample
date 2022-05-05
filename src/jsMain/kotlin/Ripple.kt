import org.w3c.dom.Element

@JsModule("@material/ripple")
@JsNonModule
external object MDCRippleModule {
    object MDCRipple {
        fun attachTo(root: Element?, opts: RippleOption)
    }
}

external interface RippleOption {
    var unbounded: Boolean
}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun attachRippleTo(
    root: Element?,
    opts: RippleOption.() -> Unit = {},
) = MDCRippleModule.MDCRipple.attachTo(root, (js("({})") as RippleOption).apply(opts))


