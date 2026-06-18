package com.innoventes.panverify.ui


import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innoventes.panverify.R
import com.innoventes.panverify.viewmodel.PanViewModel


@Composable
fun PanScreen(viewModel: PanViewModel) {

    val state by viewModel.uiState.observeAsState()

    val context = LocalContext.current
    val activity = context as Activity

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))


        // Text heading
        Text(
            text = "First of the few steps to set you up with a Bank Account",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(80.dp))

        // Pan Number Box
        Text(
            text = "PAN NUMBER",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = state?.pan ?: "",
            onValueChange = {
                if (it.length <= 10)
                    viewModel.updatePan(it)
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontWeight = FontWeight.Bold)

        )

        Spacer(modifier = Modifier.height(24.dp))

        // Birthday Box
        Text(
            text = "BIRTHDAY",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            fontSize = 13.sp,

        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // day
            OutlinedTextField(
                value = state?.day ?: "",
                onValueChange = {
                    if (it.length <= 2)
                        viewModel.updateDay(it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(90.dp),
                textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )
            // month
            OutlinedTextField(
                value = state?.month ?: "",
                onValueChange = {
                    if (it.length <= 2)
                        viewModel.updateMonth(it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(90.dp),
                textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )
             // year
            OutlinedTextField(
                value = state?.year ?: "",
                onValueChange = {
                    if (it.length <= 4)
                        viewModel.updateYear(it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(120.dp),
                textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // learn more
        Text(
            buildAnnotatedString {
                append("Providing PAN & Date of Birth helps us find and fetch your KYC from a central registry by the Government of India. ")
                withStyle(SpanStyle(color = Color.Blue)) {
                    append("Learn more")
                }
            },
            fontSize = 14.sp
        )


        Spacer(modifier = Modifier.height(8.dp))

        // button Next
        Button(
            onClick = {
                Toast.makeText(context, "Details submitted successfully", Toast.LENGTH_SHORT).show()
                activity.finish()
            },
            enabled = state?.isFormValid == true,
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("NEXT")
        }

        // Text I don't have a PAN
        TextButton(
            onClick = {
                activity.finish()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("I don't have a PAN")
        }
    }

}
