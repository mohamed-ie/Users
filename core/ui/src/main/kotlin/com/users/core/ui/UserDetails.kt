package com.users.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.users.core.model.Address
import com.users.core.model.Company
import com.users.core.model.User

@Composable
fun UserDetailsAlertDialog(
    user: User,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.core_ui_action_dimsiss))
            }
        },
        title = { Text(user.username) },
        text = {
            UserDetails(
                user = user,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            )
        }
    )
}

@Composable
fun UserDetails(
    user: User,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = stringResource(R.string.core_ui_text_name_arg1, user.name))

        Text(text = stringResource(R.string.core_ui_text_email_arg1, user.email))

        Text(text = stringResource(R.string.core_ui_text_phone_number_arg1, user.phone))

        Text(text = stringResource(R.string.core_ui_text_website_arg1, user.email))

        HorizontalDivider()

        AddressDetails(address = user.address)

        HorizontalDivider()

        CompanyDetails(company = user.company)
    }
}

@Composable
private fun AddressDetails(address: Address) {
    Text(text = stringResource(R.string.core_ui_text_address))

    Text(text = stringResource(R.string.core_ui_text_city_arg1, address.city))

    Text(text = stringResource(R.string.core_ui_text_street_arg1, address.street))

    Text(text = stringResource(R.string.core_ui_text_suite_arg1, address.suite))

    Text(text = stringResource(R.string.core_ui_text_zipcode_arg1, address.zipcode))
}

@Composable
private fun CompanyDetails(company: Company) {
    Text(text = stringResource(R.string.core_ui_text_company))

    Text(text = stringResource(R.string.core_ui_text_name_arg1, company.name))

    Text(
        text = stringResource(
            R.string.core_ui_text_catch_phrase_arg1,
            company.catchPhrase
        )
    )

    Text(
        text = stringResource(
            R.string.core_ui_text_business_stuff_arg1,
            company.businessStuff
        )
    )
}