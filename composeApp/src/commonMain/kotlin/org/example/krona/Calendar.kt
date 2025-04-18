package org.example.krona

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import krona.composeapp.generated.resources.Res
import krona.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import kotlinx.datetime.*
import androidx.compose.runtime.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign

data class Calendar2(
    val date: LocalDate,
    val title: String,
    val time: String,
    val location: String
)

@Composable
fun Calendar() {
    val events = listOf(
        Calendar2(date = LocalDate(2024, 9, 26), title = "Тур с резидентами клуба в Беларусь", time = "12:00", location = "Выезд"),
        Calendar2(date = LocalDate(2024, 9, 16), title = "Мероприятие 1", time = "14:00", location = "Зал 1"),
        Calendar2(date = LocalDate(2024, 9, 20), title = "Мероприятие 2", time = "16:00", location = "Зал 2")
    )
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F1F1))
                .padding(horizontal = 20.dp, vertical = 40.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    NowSectionActivity(events)
                }
                item {
                    CalendarSection(events)
                }
            }
        }
    }
}

@Composable
fun NowSectionActivity(events: List<Calendar2>) {
    val upcomingEvent = events.firstOrNull { it.date >= Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date }

    Text(
        text = "Предстоящие мероприятия",
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        color = Color(0xFF0F1C2E),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    )

    if (upcomingEvent != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
                .clip(RoundedCornerShape(25.dp)),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = Color(0xFF0F1C2E)
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "${upcomingEvent.date} - ${upcomingEvent.time}",
                        fontWeight = FontWeight.W600,
                        color = Color(0xFFFFE7D3),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = upcomingEvent.title,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    } else {
        Text(
            text = "Предстоящих мероприятий нет",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color(0xFF0F1C2E),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
    }
}

@Composable
fun CalendarSection(events: List<Calendar2>) {
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    val firstDayOfNextMonth = if (currentDate.monthNumber == 12) {
        LocalDate(currentDate.year + 1, 1, 1)
    } else {
        LocalDate(currentDate.year, currentDate.monthNumber + 1, 1)
    }
    val lastDayOfCurrentMonth = firstDayOfNextMonth.minus(1, DateTimeUnit.DAY)
    val daysInMonth = lastDayOfCurrentMonth.dayOfMonth

    val firstDayOfWeek = LocalDate(currentDate.year, currentDate.monthNumber, 1).dayOfWeek.ordinal

    val highlightedDays = setOf(6, 10, 17, 19, 26)
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Календарь мероприятий",
            fontFamily = raleway,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color(0xFF0F1C2E),
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(10.dp),
            painter = painterResource(Res.drawable.Arrow),
            contentDescription = null,
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp)),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFF0F1C2E)
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Сентябрь 2024",
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
                color = Color(0xFFFFE7D3),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 8.dp)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                val daysOfWeek = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
                for (day in daysOfWeek) {
                    Text(
                        text = day,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp, start = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFFFFE7D3),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFF6F635A).copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.height(8.dp))

            var dayCounter = 1
            for (week in 0 until 6) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (day in 0 until 7) {
                        if (week == 0 && day < firstDayOfWeek || dayCounter > daysInMonth) {
                            Spacer(modifier = Modifier.weight(1f))
                        } else {
                            val dayDate = LocalDate(currentDate.year, currentDate.monthNumber, dayCounter)
                            val event = events.find { it.date == dayDate }

                            if (dayCounter in highlightedDays) {
                                val circleColor = when {
                                    dayDate < currentDate -> Color(0xFFFF7878)
                                    dayDate >= currentDate -> Color(0xFF78FF9E)
                                    else -> Color.Transparent
                                }

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(3.dp)
                                        .size(25.dp)
                                        .border(2.dp, circleColor, CircleShape)
                                ) {
                                    Text(
                                        text = dayCounter.toString(),
                                        fontWeight = FontWeight.W600,
                                        color = if (event != null) Color.White else Color(0xFFFFE7D3),
                                        fontSize = 14.sp
                                    )
                                }
                            } else {
                                Text(
                                    text = dayCounter.toString(),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(4.dp),
                                    fontWeight = FontWeight.W600,
                                    color = Color(0xFFFFE7D3),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            dayCounter++
                        }
                    }
                }
                if (dayCounter > daysInMonth) break
            }
        }
    }
}