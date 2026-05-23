package me.egil.estadosjectpackcompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormTax(myViewModel: FormTaxViewModel) {

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(18.dp)
                .fillMaxSize()
        ) {

            // --- Campo: Cantidad ---
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = myViewModel.inputCantidad,
                onValueChange = { myViewModel.updateCantidad(it) },
                label = { Text(text = "Cantidad") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo: Porcentaje ---
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = myViewModel.inputPorcentaje,
                onValueChange = { myViewModel.updatePorcentaje(it)},
                label = { Text(text = "Porcentaje") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Percent,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- Switch: Redondear resultado ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (myViewModel.switchRedondear) "Resultado redondeado" else "Resultado exacto",
                    fontSize = 16.sp
                )
                Switch(
                    checked = myViewModel.switchRedondear,
                    onCheckedChange = { myViewModel.updateRedondear(it)}
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Impuesto: ${myViewModel.impuesto}",
                fontSize = 24.sp
            )
        }
    }
}
