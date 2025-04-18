import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import krona.composeapp.generated.resources.Arrow
import krona.composeapp.generated.resources.Profile
import krona.composeapp.generated.resources.Res
import org.example.krona.raleway
import org.jetbrains.compose.resources.painterResource

@Composable
fun PersonalAccount() {
    var selectedCity by remember { mutableStateOf<String?>(null) }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F1F1))
                .padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ProfileSection()
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    CitySection(selectedCity = selectedCity) { city ->
                        selectedCity = city
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileSection() {
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(55.dp),
                painter = painterResource(Res.drawable.Profile),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Янис Кайшев",
                    fontWeight = FontWeight.W600,
                    color = Color(0xFFFFE7D3),
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Резидент клуба",
                    fontWeight = FontWeight.W600,
                    color = Color(0xFF8F8F8F).copy(alpha = 0.5f),
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .size(10.dp),
                colorFilter = ColorFilter.tint(Color(0xFFFFE7D3)),
                painter = painterResource(Res.drawable.Arrow),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun CitySection(selectedCity: String?, onCitySelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(Color(0xFF0F1C2E))
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = "Ваш город",
            fontWeight = FontWeight.W700,
            color = Color(0xFFFFE7D3),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(2.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFF6F635A))
        )
        Spacer(modifier = Modifier.height(2.dp))

        val cities = listOf(
            "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород",
            "Казань", "Челябинск", "Самара", "Омск", "Ростов-на-Дону"
        )

        cities.forEachIndexed { index, city ->
            CityCard(
                cityName = city,
                isSelected = selectedCity == city,
                onClick = {
                    onCitySelected(city)
                }
            )
            Spacer(modifier = Modifier.height(2.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFF6F635A))
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun CityCard(cityName: String, isSelected: Boolean, onClick: () -> Unit) {
    val circleColor = if (isSelected) Color.Red else Color(0xFF8F8F8F)
    val dotColor = if (isSelected) Color.Black else Color.Transparent

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(25.dp)),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFF0F1C2E)
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(dotColor)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = cityName,
                fontFamily = raleway,
                fontWeight = FontWeight.W400,
                color = Color(0xFFFFE7D3),
                fontSize = 15.sp
            )
        }
    }
}