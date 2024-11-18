package com.users.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohammedie.desgin_system.theme.UsersTheme

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.core_ui_error_message_unexpected_error),
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = onRetryClick) {
            Text(text = stringResource(R.string.core_ui_action_retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UsersTheme {
        ErrorState { }
    }
}