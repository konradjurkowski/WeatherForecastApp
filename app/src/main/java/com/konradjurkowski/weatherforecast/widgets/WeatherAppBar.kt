package com.konradjurkowski.weatherforecast.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konradjurkowski.weatherforecast.R

@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    onAddActionClicked: () -> Unit = { },
    onButtonClicked: () -> Unit = { },
    onMenuItemClicked: (DropDownMenuItems) -> Unit = { }
) {
    val showDialog = remember { mutableStateOf(false) }
    ShowSettingDropDownMenu(
        showDialog = showDialog,
        onMenuItemClicked = onMenuItemClicked
    )
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = onAddActionClicked) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search_icon"
                    )
                }
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "menu_icon"
                    )
                }
            } else {
                Box { /* no op */ }
            }
        },
        navigationIcon = {
            icon?.let {
                IconButton(
                    onClick = onButtonClicked
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "search_icon",
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>,
    onMenuItemClicked: (DropDownMenuItems) -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(
                top = 45.dp,
                right = dimensionResource(id = R.dimen.extra_large_padding)
            )
    ) {
        DropdownMenu(
            expanded = showDialog.value,
            onDismissRequest = { showDialog.value = false },
            modifier = Modifier
                .width(150.dp)
                .background(Color.White)
        ) {
            DropDownMenuItems.values().toList().forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        showDialog.value = false
                        onMenuItemClicked(item)
                    }) {
                    Icon(
                        imageVector = when (item) {
                            DropDownMenuItems.ABOUT -> Icons.Default.Info
                            DropDownMenuItems.FAVORITES -> Icons.Default.FavoriteBorder
                            DropDownMenuItems.SETTINGS -> Icons.Default.Settings
                        },
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding)))
                    Text(text = stringResource(id = item.titleRes))
                }
            }
        }
    }
}

enum class DropDownMenuItems(
    val titleRes: Int
) {
    ABOUT(R.string.about_item_label),
    FAVORITES(R.string.favorites_item_label),
    SETTINGS(R.string.settings_item_label)
}
