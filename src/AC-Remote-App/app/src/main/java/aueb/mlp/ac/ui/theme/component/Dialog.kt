package aueb.mlp.ac.ui.theme.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleAlertDialogInGreek(
    onDismiss: () -> Unit = {},
    onAccept: () -> Unit = {},
    onReject: () -> Unit = {},
    title: String,
    text: String
) {
    AlertDialog( //TODO: button letters should be larger
        onDismissRequest = onDismiss,
        confirmButton = {
            PlainTextButton(
                onClick = onAccept,
                enabled = true,
                text="Ναι",
                modifier = Modifier
                    .fillMaxWidth(0.3f),
                textSizeVariation = TextSizeVariation.BODY_LARGE,
            )
        },
        dismissButton = {
            PlainTextButton(
                onClick = onReject,
                enabled = true,
                text="Όχι",
                modifier = Modifier
                    .fillMaxWidth(0.3f),
                textSizeVariation = TextSizeVariation.BODY_LARGE,
            )
        },
        title = { AcText(
            text = title,
            textSizeVariation = TextSizeVariation.BODY_LARGE,
        ) },
        text = { AcText(
            text = text,
            textSizeVariation = TextSizeVariation.BODY_MEDIUM,
        ) },
    )
}
