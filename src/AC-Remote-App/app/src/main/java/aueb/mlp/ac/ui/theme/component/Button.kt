package aueb.mlp.ac.ui.theme.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import aueb.mlp.ac.ui.theme.ACShapes

data class AcButtonColors(
    val containerColor: Color,
    val contentColor: Color,
) {
    companion object {
        val Disabled = AcButtonColors(
            containerColor = Color(0xFFC5C7F1),
            contentColor = Color(0x80000000),
        )
        val Enabled = AcButtonColors(
            containerColor = Color(0xFFECF0FF),
            contentColor = Color(0xFF000000),
        )
        val Selected = AcButtonColors(
            containerColor = Color(0xFF008DAC),
            contentColor = Color(0xFFFFFFFF),
        )
    }
}

@Composable
fun PlainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    enabledColors: AcButtonColors = AcButtonColors.Enabled,
    disabledColors: AcButtonColors = AcButtonColors.Disabled,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(onClick = { if (enabled) onClick() })
            .padding(10.dp)
            .clip(shape = ACShapes.medium)
            .background(if (enabled) enabledColors.containerColor else disabledColors.containerColor)
            .then(modifier)
            // moved after .then because first size is used
            .wrapContentHeight()
            .size(300.dp, 100.dp)
    ) {
        content()
    }
}

@Composable
/**
 * `!enabled` is checked first; disabled buttons have highest precedence. `selected` is checked
 * second; enabled buttons change depending on whether they are selected. If `!selected` fall back
 * to enabledColors.
 */
fun StatefulButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    enabledColors: AcButtonColors = AcButtonColors.Enabled,
    disabledColors: AcButtonColors = AcButtonColors.Disabled,
    selected: Boolean,
    selectedColors: AcButtonColors = AcButtonColors.Selected,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(onClick = { if (enabled) onClick() })
            .padding(10.dp)
            .clip(shape = ACShapes.medium)
            .background(
                if (!enabled)
                    disabledColors.containerColor
                else
                    if (selected) selectedColors.containerColor
                    else enabledColors.containerColor
            )
            .then(modifier)
            // moved after .then because first size is used
            .wrapContentHeight()
            .size(300.dp, 100.dp)
    ) {
        content()
    }
}

@Composable
fun PlainTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    enabledColors: AcButtonColors = AcButtonColors.Enabled,
    disabledColors: AcButtonColors = AcButtonColors.Disabled,
) {
    PlainButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        enabledColors = enabledColors,
        disabledColors = disabledColors,
    ) {
        Text(text = text)
    }
}

@Composable
fun StatefulTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    enabledColors: AcButtonColors = AcButtonColors.Enabled,
    disabledColors: AcButtonColors = AcButtonColors.Disabled,
    selected: Boolean,
    selectedColors: AcButtonColors = AcButtonColors.Selected,
) {
    StatefulButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        enabledColors = enabledColors,
        disabledColors = disabledColors,
        selected = selected,
        selectedColors = selectedColors,
    ) {
        Text(text = text)
    }
}

@Composable
fun PlainIconButton(
    @DrawableRes id: Int,
    alt: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    enabledColors: AcButtonColors = AcButtonColors.Enabled,
    disabledColors: AcButtonColors = AcButtonColors.Disabled,
) {
    PlainButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        enabledColors = enabledColors,
        disabledColors = disabledColors,
    ) {
        Icon(
            id = id,
            alt = alt,
        )
    }
}

@Composable
fun ModeButton(
    text: String,
    @DrawableRes id: Int,
    alt: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    enabledColors: AcButtonColors = AcButtonColors.Enabled,
    disabledColors: AcButtonColors = AcButtonColors.Disabled,
    selected: Boolean,
    selectedColors: AcButtonColors = AcButtonColors.Selected,
) {
    StatefulButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        enabledColors = enabledColors,
        disabledColors = disabledColors,
        selected = selected,
        selectedColors = selectedColors,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp)
        ) {
            Text(text = text)
            Icon(
                id = id,
                alt = alt,
            )
        }
    }
}