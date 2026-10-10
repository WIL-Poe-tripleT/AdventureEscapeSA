package com.example.adventureescapesa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adventureescapesa.ui.theme.AdventureEscapeSATheme
import java.util.Locale

private val ForestGreen = Color(0xFF2E7D32)
private val SkyBlue = Color(0xFF4FC3F7)
private val MountainGrey = Color(0xFF616161)
private val SunsetOrange = Color(0xFFFF9800)
private val LightGreen = Color(0xFFE8F5E9)
private val PageBackground = Color(0xFFF8FAF7)

data class Adventure(
    val name: String,
    val description: String,
    val duration: String,
    val difficulty: String,
    val adultPrice: Double,
    val childPrice: Double,
    val symbol: String
)

private val adventures = listOf(
    Adventure(
        name = "Mountain Hiking",
        description = "Explore scenic mountain trails, enjoy beautiful landscapes and experience the outdoors. This adventure is ideal for people who enjoy walking, nature and fresh air.",
        duration = "3–4 hours",
        difficulty = "Moderate",
        adultPrice = 500.0,
        childPrice = 250.0,
        symbol = "⛰"
    ),
    Adventure(
        name = "Water Adventures",
        description = "Discover exciting water-based experiences and enjoy the natural beauty of the surrounding environment. Remember to follow the activity safety instructions.",
        duration = "2–3 hours",
        difficulty = "Moderate",
        adultPrice = 650.0,
        childPrice = 325.0,
        symbol = "🌊"
    ),
    Adventure(
        name = "Outdoor Exploration",
        description = "Enjoy the outdoors, discover natural surroundings and spend time exploring with friends, family or a group.",
        duration = "2 hours",
        difficulty = "Easy",
        adultPrice = 450.0,
        childPrice = 225.0,
        symbol = "🌿"
    )
)

private fun formatRand(amount: Double): String {
    return "R" + String.format(Locale.US, "%,.2f", amount)
}

@Composable
fun AdventureLogo(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    Image(
        painter = painterResource(
            id = R.drawable.adventure_escape_logo
        ),
        contentDescription = "Adventure Escape SA Logo",
        modifier = modifier.size(size),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ScreenHeader(
    title: String,
    showBack: Boolean = false,
    onBack: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (showBack) {
            OutlinedButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text("Back")
            }
        } else {
            Text(
                text = title,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = ForestGreen,
                modifier = Modifier.weight(1f)
            )
        }

        AdventureLogo(size = 48.dp)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AdventureEscapeSATheme {
                AdventureEscapeApp()
            }
        }
    }
}

@Composable
fun AdventureEscapeApp() {
    var selectedScreen by remember { mutableStateOf("Welcome") }
    var selectedAdventure by remember { mutableStateOf(adventures.first()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PageBackground,
        bottomBar = {
            if (
                selectedScreen != "Individual Adventure" &&
                selectedScreen != "Welcome"
            ) {
                NavigationBar(containerColor = Color.White) {
                    NavigationBarItem(
                        selected = selectedScreen == "Home",
                        onClick = { selectedScreen = "Home" },
                        icon = {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        },
                        label = { Text("Home") }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == "About",
                        onClick = { selectedScreen = "About" },
                        icon = {
                            Icon(
                                Icons.Default.Info,
                                contentDescription = "About Us"
                            )
                        },
                        label = { Text("About") }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == "Overview",
                        onClick = { selectedScreen = "Overview" },
                        icon = {
                            Icon(
                                Icons.Default.List,
                                contentDescription = "Overview"
                            )
                        },
                        label = { Text("Overview") }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == "Fees",
                        onClick = { selectedScreen = "Fees" },
                        icon = {
                            Icon(
                                Icons.Default.AccountBox,
                                contentDescription = "Calculate Fees"
                            )
                        },
                        label = { Text("Fees") }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == "Contact",
                        onClick = { selectedScreen = "Contact" },
                        icon = {
                            Icon(
                                Icons.Default.Call,
                                contentDescription = "Contact Us"
                            )
                        },
                        label = { Text("Contact") }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedScreen) {
            "Welcome" -> WelcomeScreen(
                modifier = Modifier.padding(innerPadding),
                onEnterClick = { selectedScreen = "Home" }
            )

            "Home" -> HomeScreen(
                modifier = Modifier.padding(innerPadding),
                onExploreClick = { selectedScreen = "Overview" },
                onAdventureClick = {
                    selectedAdventure = it
                    selectedScreen = "Individual Adventure"
                }
            )

            "About" -> AboutScreen(
                modifier = Modifier.padding(innerPadding)
            )

            "Overview" -> OverviewScreen(
                modifier = Modifier.padding(innerPadding),
                onAdventureClick = {
                    selectedAdventure = it
                    selectedScreen = "Individual Adventure"
                }
            )

            "Individual Adventure" -> IndividualAdventureScreen(
                adventure = selectedAdventure,
                onBack = { selectedScreen = "Overview" },
                onBook = { selectedScreen = "Fees" }
            )

            "Fees" -> FeesScreen(
                modifier = Modifier.padding(innerPadding),
                initialAdventure = selectedAdventure
            )

            "Contact" -> ContactScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onEnterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PageBackground)
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AdventureLogo(size = 220.dp)

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "ADVENTURE ESCAPE SA",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Explore. Experience. Escape.",
            fontSize = 17.sp,
            color = MountainGrey,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = onEnterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ForestGreen
            )
        ) {
            Text(
                text = "Explore Adventures",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onExploreClick: () -> Unit,
    onAdventureClick: (Adventure) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(PageBackground)
            .padding(20.dp)
    ) {
        ScreenHeader(title = "Home")

        Text(
            text = "ADVENTURE ESCAPE SA",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Explore. Experience. Escape.",
            fontSize = 15.sp,
            color = MountainGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = ForestGreen
            )
        ) {
            Column(modifier = Modifier.padding(22.dp)) {
                Text(
                    text = "Your Next Adventure Starts Here",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 30.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Discover outdoor experiences, explore beautiful destinations and plan your next unforgettable escape.",
                    fontSize = 15.sp,
                    color = Color.White,
                    lineHeight = 23.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onExploreClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SunsetOrange
                    )
                ) {
                    Text(
                        "Explore Adventures",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Popular Activities",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(14.dp))

        adventures.forEach { adventure ->
            HomeActivityCard(
                adventure = adventure,
                onClick = { onAdventureClick(adventure) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun HomeActivityCard(
    adventure: Adventure,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        LightGreen,
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = adventure.symbol,
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = adventure.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreen
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = adventure.description,
                    fontSize = 13.sp,
                    color = MountainGrey,
                    lineHeight = 18.sp,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        ScreenHeader(title = "About Us")

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightGreen
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Adventure Escape SA",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreen
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "We help people discover outdoor adventures and explore the natural beauty of South Africa.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Our Story",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Adventure Escape SA is designed to make discovering adventure activities easier. Visitors can browse experiences, learn about activities and estimate costs before planning an adventure.",
            fontSize = 15.sp,
            lineHeight = 23.sp
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Our Mission",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Our mission is to help individuals, families, tourists, schools and organisations find outdoor experiences that suit their interests and plans.",
            fontSize = 15.sp,
            lineHeight = 23.sp
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Who We Serve",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoCard(
            title = "Families and Tourists",
            description = "Discover activities and destinations for memorable trips."
        )

        Spacer(modifier = Modifier.height(10.dp))

        InfoCard(
            title = "Schools and Groups",
            description = "Explore outdoor experiences suitable for group planning."
        )

        Spacer(modifier = Modifier.height(10.dp))

        InfoCard(
            title = "Adventure Seekers",
            description = "Find exciting ways to enjoy the outdoors."
        )
    }
}

@Composable
fun InfoCard(
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = ForestGreen
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = MountainGrey
            )
        }
    }
}

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    onAdventureClick: (Adventure) -> Unit
) {
    var selectedCategory by remember {
        mutableStateOf("All")
    }

    val filteredAdventures = when (selectedCategory) {
        "Hiking" -> adventures.filter {
            it.name == "Mountain Hiking"
        }

        "Water" -> adventures.filter {
            it.name == "Water Adventures"
        }

        "Outdoor" -> adventures.filter {
            it.name == "Outdoor Exploration"
        }

        else -> adventures
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        ScreenHeader(title = "Adventure Overview")

        Text(
            text = "Discover experiences for your next escape.",
            fontSize = 15.sp,
            color = MountainGrey
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            listOf("All", "Hiking", "Water", "Outdoor").forEach { category ->
                val isSelected = selectedCategory == category

                Button(
                    onClick = {
                        selectedCategory = category
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) {
                            ForestGreen
                        } else {
                            Color(0xFFE0E0E0)
                        },
                        contentColor = if (isSelected) {
                            Color.White
                        } else {
                            Color(0xFF424242)
                        }
                    )
                ) {
                    Text(
                        text = category,
                        fontSize = 10.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        filteredAdventures.forEach { adventure ->
            AdventureOverviewCard(
                adventure = adventure,
                onClick = {
                    onAdventureClick(adventure)
                }
            )
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun AdventureOverviewCard(
    adventure: Adventure,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        SkyBlue.copy(alpha = 0.25f),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = adventure.symbol,
                    fontSize = 48.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = adventure.name,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = ForestGreen
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = adventure.description,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = MountainGrey
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "From ${formatRand(adventure.adultPrice)} per adult",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = ForestGreen
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ForestGreen
                )
            ) {
                Text("View Adventure")
            }
        }
    }
}

@Composable
fun IndividualAdventureScreen(
    adventure: Adventure,
    onBack: () -> Unit,
    onBook: () -> Unit
) {
    var isFavourite by remember(adventure.name) {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        ScreenHeader(
            title = adventure.name,
            showBack = true,
            onBack = onBack
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    isFavourite = !isFavourite
                }
            ) {
                Icon(
                    imageVector = if (isFavourite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = if (isFavourite) {
                        "Remove from favourites"
                    } else {
                        "Add to favourites"
                    },
                    tint = if (isFavourite) Color.Red else ForestGreen,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = SkyBlue.copy(alpha = 0.25f)
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = adventure.symbol,
                    fontSize = 80.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = adventure.name,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "${formatRand(adventure.adultPrice)} per adult",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = SunsetOrange
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DetailTag(
                title = "Duration",
                value = adventure.duration,
                modifier = Modifier.weight(1f)
            )

            DetailTag(
                title = "Difficulty",
                value = adventure.difficulty,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "About This Adventure",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = adventure.description,
            fontSize = 16.sp,
            lineHeight = 25.sp,
            color = Color(0xFF424242)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Adventure Highlights",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoCard(
            title = "Outdoor Experience",
            description = "Enjoy an activity designed to help you explore the outdoors."
        )

        Spacer(modifier = Modifier.height(10.dp))

        InfoCard(
            title = "Suitable Planning",
            description = "Review the activity details and estimate your group's fees before booking."
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBook,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SunsetOrange
            )
        ) {
            Text(
                text = "Book This Activity",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun DetailTag(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = title,
                fontSize = 13.sp,
                color = MountainGrey
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = ForestGreen
            )
        }
    }
}

@Composable
fun FeesScreen(
    modifier: Modifier = Modifier,
    initialAdventure: Adventure = adventures.first()
) {
    var customerName by remember {
        mutableStateOf("")
    }

    var customerEmail by remember {
        mutableStateOf("")
    }

    var adultsText by remember {
        mutableStateOf("1")
    }

    var childrenText by remember {
        mutableStateOf("0")
    }

    var selectedAdventureNames by remember(initialAdventure.name) {
        mutableStateOf(setOf(initialAdventure.name))
    }

    var calculated by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    val selectedAdventures = adventures.filter {
        selectedAdventureNames.contains(it.name)
    }

    val adults = adultsText.toIntOrNull() ?: 0
    val children = childrenText.toIntOrNull() ?: 0
    val participants = adults + children

    val subtotal = selectedAdventures.sumOf { adventure ->
        adults * adventure.adultPrice +
                children * adventure.childPrice
    }

    val discountRate = if (participants >= 10) 0.10 else 0.0
    val discount = subtotal * discountRate
    val discountedSubtotal = subtotal - discount
    val vat = discountedSubtotal * 0.15
    val total = discountedSubtotal + vat

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        ScreenHeader(title = "Calculate Fees")

        Text(
            text = "Select your adventures and enter your group details to estimate the cost.",
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color = MountainGrey
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = {
                customerName = it
                calculated = false
                errorMessage = ""
            },
            label = { Text("Customer name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = customerEmail,
            onValueChange = {
                customerEmail = it
                calculated = false
                errorMessage = ""
            },
            label = { Text("Email address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Select Adventures",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "You can select more than one activity.",
            fontSize = 14.sp,
            color = MountainGrey
        )

        Spacer(modifier = Modifier.height(10.dp))

        adventures.forEach { adventure ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (
                        selectedAdventureNames.contains(adventure.name)
                    ) {
                        LightGreen
                    } else {
                        Color.White
                    }
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                            vertical = 6.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedAdventureNames.contains(
                            adventure.name
                        ),
                        onCheckedChange = { checked ->
                            selectedAdventureNames = if (checked) {
                                selectedAdventureNames + adventure.name
                            } else {
                                selectedAdventureNames - adventure.name
                            }

                            calculated = false
                            errorMessage = ""
                        }
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        Text(
                            text = adventure.name,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen,
                            fontSize = 15.sp
                        )

                        Text(
                            text = "Adult: ${formatRand(adventure.adultPrice)}",
                            fontSize = 13.sp,
                            color = MountainGrey
                        )

                        Text(
                            text = "Child: ${formatRand(adventure.childPrice)}",
                            fontSize = 13.sp,
                            color = MountainGrey
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Number of Participants",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = adultsText,
            onValueChange = {
                adultsText = it.filter(Char::isDigit)
                calculated = false
                errorMessage = ""
            },
            label = { Text("Number of adults") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = childrenText,
            onValueChange = {
                childrenText = it.filter(Char::isDigit)
                calculated = false
                errorMessage = ""
            },
            label = { Text("Number of children") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Discount Information",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreen
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Demonstration rule: groups of 10 or more participants receive a 10% discount on the activity subtotal.",
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = MountainGrey
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                errorMessage = when {
                    customerName.isBlank() ->
                        "Please enter the customer's name."

                    !customerEmail.contains("@") ||
                            !customerEmail.substringAfter("@", "").contains(".") ->
                        "Please enter a valid email address."

                    selectedAdventureNames.isEmpty() ->
                        "Please select at least one adventure."

                    adultsText.isBlank() || childrenText.isBlank() ->
                        "Please enter both participant quantities."

                    participants <= 0 ->
                        "Please enter at least one participant."

                    participants > 1000 ->
                        "The group cannot exceed 1,000 participants."

                    else -> ""
                }

                calculated = errorMessage.isEmpty()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SunsetOrange
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Calculate Fees",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }

        if (calculated) {
            Spacer(modifier = Modifier.height(22.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightGreen
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Adventure Invoice",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestGreen
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "CUSTOMER SUMMARY",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MountainGrey
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    FeeRow("Customer", customerName)
                    FeeRow("Email", customerEmail)
                    FeeRow("Adults", adults.toString())
                    FeeRow("Children", children.toString())
                    FeeRow("Total participants", participants.toString())

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "SELECTED ACTIVITIES",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MountainGrey
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    selectedAdventures.forEach { adventure ->
                        val activityCost =
                            adults * adventure.adultPrice +
                                    children * adventure.childPrice

                        Text(
                            text = adventure.name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen
                        )

                        FeeRow(
                            "Adults × ${formatRand(adventure.adultPrice)}",
                            formatRand(adults * adventure.adultPrice)
                        )

                        FeeRow(
                            "Children × ${formatRand(adventure.childPrice)}",
                            formatRand(children * adventure.childPrice)
                        )

                        FeeRow(
                            "Activity subtotal",
                            formatRand(activityCost)
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    FeeRow("Subtotal", formatRand(subtotal))

                    FeeRow(
                        "Group discount (${(discountRate * 100).toInt()}%)",
                        "- ${formatRand(discount)}"
                    )

                    FeeRow(
                        "Subtotal after discount",
                        formatRand(discountedSubtotal)
                    )

                    FeeRow(
                        "Illustrative VAT (15%)",
                        formatRand(vat)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "ESTIMATED TOTAL",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MountainGrey
                    )

                    Text(
                        text = formatRand(total),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestGreen
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "This is an estimated invoice for demonstration purposes, not a confirmed booking. Verify activity prices, discount rules and VAT treatment before confirming a booking.",
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = MountainGrey
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun FeeRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF424242),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            color = Color(0xFF424242),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ContactScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var confirmation by remember { mutableStateOf("") }

    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        ScreenHeader(title = "Contact Us")

        Text(
            text = "Have a question about an adventure? Get in touch with Adventure Escape SA.",
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color = MountainGrey
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Get in Touch",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Phone",
                        tint = ForestGreen,
                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Phone",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen
                        )

                        Text(
                            text = "+27 21 000 0000",
                            fontSize = 14.sp,
                            color = MountainGrey
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_DIAL,
                                Uri.parse("tel:+27210000000")
                            )

                            context.startActivity(intent)
                        }
                    ) {
                        Text("Call")
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Email",
                        tint = ForestGreen,
                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Email",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen
                        )

                        Text(
                            text = "info@adventureescapesa.co.za",
                            fontSize = 13.sp,
                            color = MountainGrey
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_SENDTO,
                                    Uri.parse(
                                        "mailto:info@adventureescapesa.co.za"
                                    )
                                )

                                runCatching {
                                    context.startActivity(intent)
                                }
                            }
                        ) {
                            Text("Send Email")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Address",
                        tint = ForestGreen,
                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Visit Us",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "55 St Georges Street, Cape Town, Western Cape, 8001, South Africa",
                            fontSize = 14.sp,
                            lineHeight = 21.sp,
                            color = MountainGrey
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                val address = Uri.encode(
                                    "55 St Georges Street, Cape Town, Western Cape, 8001, South Africa"
                                )

                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(
                                        "https://www.google.com/maps/search/?api=1&query=$address"
                                    )
                                )

                                runCatching {
                                    context.startActivity(intent)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ForestGreen
                            )
                        ) {
                            Text("Open in Google Maps")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Connect With Us",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Follow Adventure Escape SA on social media for outdoor inspiration and adventure updates.",
            fontSize = 14.sp,
            lineHeight = 21.sp,
            color = MountainGrey
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/")
                        )

                        runCatching {
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SunsetOrange
                    )
                ) {
                    Text(
                        text = "Instagram",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/")
                        )

                        runCatching {
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ForestGreen
                    )
                ) {
                    Text(
                        text = "Facebook",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Demonstration links: replace these with the official Adventure Escape SA social media profile URLs when available.",
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = MountainGrey
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Send an Enquiry",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        confirmation = ""
                    },
                    label = { Text("Your name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        confirmation = ""
                    },
                    label = { Text("Email address") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = message,
                    onValueChange = {
                        message = it
                        confirmation = ""
                    },
                    label = { Text("Your message") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    minLines = 4
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        confirmation = when {
                            name.isBlank() ->
                                "Please enter your name."

                            !email.contains("@") ||
                                    !email.substringAfter("@", "").contains(".") ->
                                "Please enter a valid email address."

                            message.isBlank() ->
                                "Please enter your message."

                            else ->
                                "Thank you, $name. Your enquiry has been prepared. This demonstration form does not send messages."
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SunsetOrange
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Submit Enquiry",
                        fontWeight = FontWeight.Bold
                    )
                }

                if (confirmation.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = confirmation,
                        fontSize = 14.sp,
                        color = if (confirmation.startsWith("Thank you")) {
                            ForestGreen
                        } else {
                            Color.Red
                        },
                        lineHeight = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightGreen
            )
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "Adventure Escape SA",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestGreen
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Explore. Experience. Escape.",
                    fontSize = 14.sp,
                    color = MountainGrey
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Discover outdoor experiences and start planning your next South African adventure.",
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = MountainGrey
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}