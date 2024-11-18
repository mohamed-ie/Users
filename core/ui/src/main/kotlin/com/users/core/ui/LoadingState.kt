package com.users.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mohammedie.desgin_system.theme.UsersTheme

@Composable
fun LoadingState(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    CircularProgressIndicator()
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UsersTheme {
        LoadingState()
    }
}