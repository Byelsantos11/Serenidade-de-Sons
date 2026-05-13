package com.example.serinidadedesons.Screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.serinidadedesons.R

// Cores baseadas na sua imagem
val PrimaryTextColor = Color(0xFF2C3E50)
val ButtonColor = Color(0xFF76C7C0)
val BottomBarColor = Color(0xFFA1E3CB)
val BackgroundColor = Color(0xFFF9F9F9)

@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    
    Scaffold(
        bottomBar = { CustomBottomBar(navController) },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_serenidade),
                contentDescription = "Logo Serenidade em Sons",
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Seja Bem Vindo(a)",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryTextColor
            )

            Text(
                text = "Escolha seu Som!",
                fontSize = 14.sp,
                color = PrimaryTextColor,
                modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
            )

            CategoryButton(
                text = "Dormir",
                imageResId = R.drawable.dormi_serinidade,
                onClick = {
                    navController.navigate("Dormir")
                }
            )

            CategoryButton(
                text = "Estudar",
                imageResId = R.drawable.estudar_serenidade,
                onClick = {
                   navController.navigate("Estudar")
                }
            )

            CategoryButton(
                text = "Meditar",
                imageResId = R.drawable.meditar_serenidade,
                onClick = {
                    navController.navigate("Meditar")
                }
            )
        }
    }
}

@Composable
fun CategoryButton(
    text: String,
    imageResId: Int? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
        shape = RoundedCornerShape(50.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(70.dp)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageResId != null) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.5f))
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CustomBottomBar(navController: NavController) {
    Surface(
        color = BottomBarColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding() // Isso adiciona o preenchimento necessário para a barra de navegação do sistema
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigate("Home")
            }) {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(onClick = { /* Ação Configurações */ }) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Configurações",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}