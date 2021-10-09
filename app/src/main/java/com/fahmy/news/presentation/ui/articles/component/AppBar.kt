package com.fahmy.news.presentation.ui.articles.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppBar(
    title: String,
    query: String,
    onValueChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onToggleTheme: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = title,
                modifier = Modifier.padding(12.dp),
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )


            Row() {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = {
                        onValueChanged(it)
                    },
                    leadingIcon = {
                        Icon(Icons.Rounded.Search, contentDescription = null)
                    },
                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 12.sp
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearch()
                        }
                    )


                )

                IconButton(
                    onClick = { onToggleTheme() },
                    modifier = Modifier.align(CenterVertically)
                ) {
                    Icon(Icons.Rounded.MoreVert, contentDescription = "Localized description")
                }
            }
        }
    }

}