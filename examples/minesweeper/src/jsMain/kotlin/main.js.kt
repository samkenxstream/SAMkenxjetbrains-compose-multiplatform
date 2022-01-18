import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.renderComposable
import org.jetbrains.compose.web.ui.Styles
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window("Falling Balls") {
            Game()
        }
    }
}

@Composable
actual fun CellWithIcon(src: String, alt: String) {
//    Image(
//        painter = painterResource(src),
//        contentDescription = alt,
//        modifier = Modifier.fillMaxSize().padding(Dp(4.0f))
//    )
}

@Composable
actual fun OpenedCell(cell: Cell) {
    Text(
        text = cell.bombsNear.toString(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
actual fun NewGameButton(text: String, onClick: () -> Unit) {
    Box(
        Modifier
            .background(color = Color(0x42, 0x8e, 0x04))
            .border(width = 1.dp,  color = Color.White)
            .clickable { onClick() }
    ) {
        androidx.compose.material.Text(text,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun ClickableCell(
    onLeftMouseButtonClick: (isShiftPressed: Boolean) -> Unit,
    onRightMouseButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {

            }.pointerInput(Unit) {
                detectTapGestures(onTap = {
                    onLeftMouseButtonClick(false)
                })
            }
//            .mouseClickable {
//                val lmb = buttons.isPrimaryPressed
//                val rmb = buttons.isSecondaryPressed
//
//                if (lmb && !rmb) {
//                    onLeftMouseButtonClick(keyboardModifiers.isShiftPressed)
//                } else if (rmb && !lmb) {
//                    onRightMouseButtonClick()
//                }
//            }
    ) {
        content()
    }
}
