package org.example.krona

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.graphicsLayer
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.layout.ContentScale
import krona.composeapp.generated.resources.Res
import krona.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun Events() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F1F1))
                .padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    CustomCard("Конференции", Res.drawable.conferences, Modifier.weight(0.2f))
                    CustomCard("Круглый стол", Res.drawable.round_table, Modifier.weight(0.2f))
                    CustomCard("Выставки", Res.drawable.exposition, Modifier.weight(0.1f))
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    CustomCard("Тренинги", Res.drawable.trainings, Modifier.weight(0.1f))
                    CustomCard("Тимбилдинг", Res.drawable.team_building, Modifier.weight(0.2f))
                    CustomCard("Презентация продуктов", Res.drawable.product_presentation, Modifier.weight(0.2f))
                }
            }

            CustomCard("Прошедшие мероприятия", Res.drawable.past_events, Modifier.fillMaxWidth().weight(0.35f))
        }
    }
}

@Composable
private fun CustomCard(text: String, imageResource: DrawableResource, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, end = 2.dp, start = 2.dp)
            .clip(RoundedCornerShape(25.dp)),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFF0F1C2E)
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().graphicsLayer(alpha = 0.5f),
                contentScale = ContentScale.Crop
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W700,
                    fontFamily = raleway,
                    color = Color(0xFFFFE7D3),
                    fontSize = 18.sp
                )
            }
        }
    }
}