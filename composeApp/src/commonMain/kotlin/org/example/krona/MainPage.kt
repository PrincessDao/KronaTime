package org.example.krona

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import krona.composeapp.generated.resources.Res
import krona.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import kotlinx.datetime.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Event(val title: String, val time: String, val room: String, val event_manager: String)

val schedule = listOf(
    Event("Обучение", "10:00-16:30", "Общий зал", "Газпром"),
    Event("Индивидуальное собрание", "17:00-18:00", "1 переговорная", "инд."),
    Event("Круглый стол", "20:00-22:00", "3 этаж, 2 каб.", "Янис Кайшев"),
)

@Composable
fun MainPage() {
    var currentEvent by remember { mutableStateOf<Event?>(null) }

    LaunchedEffect(Unit) {
        while (true) {
            currentEvent = schedule.find { isEventNow(it) }
            delay(10000)
        }
    }

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
                    NowSection(currentEvent)
                }
                item {
                    ScheduleSection()
                }
                itemsIndexed(schedule) { index, event ->
                    ScheduleItem(event, isFirst = index == 0, isLast = index == schedule.lastIndex)
                }
            }
        }
    }
}

fun isEventNow(event: Event): Boolean {
    val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
    val (startTimeString, endTimeString) = event.time.split("-")

    val startTime = LocalTime.parse(startTimeString)
    val endTime = LocalTime.parse(endTimeString)

    return currentTime >= startTime && currentTime <= endTime
}

@Composable
fun NowSection(currentEvent: Event?) {

    val alpha = remember { Animatable(1f) }

    val (startTime1, endTime1) = currentEvent?.time?.split("-") ?: listOf("", "")

    LaunchedEffect(Unit) {
        while (true) {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        }
    }

    if (currentEvent != null) {
        Text(
            text = "Сейчас",
            fontFamily = raleway,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color(0xFF0F1C2E),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
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
                    .padding(top = 8.dp, end = 16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Spacer(modifier = Modifier.height(4.dp))
                    Card(
                        modifier = Modifier
                            .height(15.dp)
                            .width(20.dp)
                            .clip(RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp))
                            .graphicsLayer(alpha = alpha.value),
                        shape = RectangleShape,
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = Color(0xFF78FF9E)
                        ),
                    ) {}
                }
                Spacer(modifier = Modifier.width(7.dp))
                Column(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                ) {

                    Text(
                        text = currentEvent!!.title,
                        fontFamily = raleway,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFFFFE7D3),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = currentEvent!!.room,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = currentEvent!!.event_manager,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Row {
                        Text(
                            text = startTime1,
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                            fontSize = 15.sp
                        )
                        Text(
                            text = "-",
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                            fontSize = 15.sp
                        )
                    }
                    Text(
                        text = endTime1,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                        fontSize = 15.sp
                    )
                }
            }
        }
    } else {
        Text(
            text = "Сейчас нет активных мероприятий",
            fontFamily = raleway,
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
fun ScheduleSection() {
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val formattedDate = formatCurrentDate(currentDate)
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Расписание",
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
            .height(55.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        shape = RectangleShape,
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFF0F1C2E)
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formattedDate,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
                color = Color(0xFFFFE7D3),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color(0xFF6F635A))
    )
}

fun formatCurrentDate(date: LocalDate): String {
    val daysOfWeek = listOf(
        "ПОНЕДЕЛЬНИК", "ВТОРНИК", "СРЕДА", "ЧЕТВЕРГ", "ПЯТНИЦА", "СУББОТА", "ВОСКРЕСЕНЬЕ"
    )

    val months = listOf(
        "ЯНВАРЯ", "ФЕВРАЛЯ", "МАРТА", "АПРЕЛЯ", "МАЯ", "ИЮНЯ",
        "ИЮЛЯ", "АВГУСТА", "СЕНТЯБРЯ", "ОКТЯБРЯ", "НОЯБРЯ", "ДЕКАБРЯ"
    )

    val dayOfWeek = daysOfWeek[date.dayOfWeek.ordinal]

    val month = months[date.monthNumber - 1]

    return "$dayOfWeek, ${date.dayOfMonth} $month"
}

@Composable
fun ScheduleItem(event: Event, isFirst: Boolean, isLast: Boolean) {
    val shape: Shape = when {
        isLast -> RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
        else -> RectangleShape
    }

    val (startTime, endTime) = event.time.split("-")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
                .clip(shape),
            shape = shape,
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = Color(0xFF0F1C2E)
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, end = 16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Spacer(modifier = Modifier.height(4.dp))
                    Card(
                        modifier = Modifier
                            .height(15.dp)
                            .width(20.dp)
                            .clip(RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp)),
                        shape = RectangleShape,
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = Color(0xFFFFE7D3)
                        ),
                    ) {}
                }
                Spacer(modifier = Modifier.width(7.dp))
                Column(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                ) {
                    Text(
                        text = event.title,
                        fontFamily = raleway,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFFFFE7D3),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = event.room,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = event.event_manager,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Row {
                        Text(
                            text = startTime,
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                            fontSize = 15.sp
                        )
                        Text(
                            text = "-",
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                            fontSize = 15.sp
                        )
                    }
                    Text(
                        text = endTime,
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                        fontSize = 15.sp
                    )
                }
            }
        }

        if (!isLast) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFF6F635A))
            )
        }
    }
}