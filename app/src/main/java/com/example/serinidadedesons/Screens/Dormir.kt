package com.example.serinidadedesons.Screens

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.serinidadedesons.R

// Cores
val IconGreenColor = Color(0xFF9AE0CA)
val IconPurpleColor = Color(0xFF8E44AD)

@Composable
fun DormirScreen(navController: NavController) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    // ESTADOS DO ÁUDIO E TIMER
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var somAtual by remember { mutableStateOf<Int?>(null) }
    var timerAtivo by remember { mutableStateOf(false) }


    // ESTADOS DOS MENUS SANFONA
    var isRuidosBrancosExpanded by remember { mutableStateOf(false) }
    var isChuvasExpanded by remember { mutableStateOf(false) }
    var isSuaveExpanded by remember { mutableStateOf(false) }


    // LÓGICA DE ÁUDIO E TIMER
    fun toggleAudio(audioResId: Int) {
        if (somAtual == audioResId && isPlaying) {
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, audioResId).apply {
                isLooping = true
                start()
            }
            somAtual = audioResId
            isPlaying = true
        }
    }

    fun iniciarTimer(minutos: Int) {
        if (!isPlaying) return

        timerAtivo = true
        coroutineScope.launch {
            val tempoEmMilissegundos = minutos * 60 * 1000L
            delay(tempoEmMilissegundos)

            if (isPlaying) {
                mediaPlayer?.pause()
                isPlaying = false
            }
            timerAtivo = false
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    // INTERFACE (UI)
    Scaffold(
        bottomBar = { CustomBottomBar(navController) },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {

            // Botão de Voltar no topo esquerdo
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = IconGreenColor,
                    modifier = Modifier.size(40.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagem Principal
                Image(
                    painter = painterResource(id = R.drawable.dormi_serinidade),
                    contentDescription = "Imagem de pessoa dormindo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(130.dp)
                        .clip(RoundedCornerShape(100.dp))
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Logo Menor
                Image(
                    painter = painterResource(id = R.drawable.logo_serenidade),
                    contentDescription = "Logo Serenidade",
                    modifier = Modifier.size(230.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // GRUPO 1: Ruidos Brancos
                SoundListItem(
                    title = "Ruidos Brancos",
                    icon = if (isRuidosBrancosExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isRuidosBrancosExpanded = !isRuidosBrancosExpanded }
                )
                AnimatedVisibility(visible = isRuidosBrancosExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        SoundListItem(
                            title = "Ruido Leve",
                            iconDrawable = if (isPlaying && somAtual == R.raw.ruido_branco_leve) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.ruido_branco_leve) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.ruido_branco_leve) }
                        )

                        SoundListItem(
                            title = "Ruido Dark",
                            iconDrawable = if (isPlaying && somAtual == R.raw.ruido_branco_dark) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.ruido_branco_dark) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.ruido_branco_dark) }
                        )
                    }
                }

                // ==========================================
                // GRUPO 2: Chuvas
                // ==========================================
                SoundListItem(
                    title = "Chuvas",
                    icon = if (isChuvasExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isChuvasExpanded = !isChuvasExpanded }
                )

                AnimatedVisibility(visible = isChuvasExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {

                        SoundListItem(
                            title = "Chuva Leve",
                            iconDrawable = if (isPlaying && somAtual == R.raw.chuva_leve) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.chuva_leve) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.chuva_leve) }
                        )

                        SoundListItem(
                            title = "Chuva Forte",
                            iconDrawable = if(isPlaying && somAtual == R.raw.chuva_forte) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.chuva_forte) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.chuva_forte) }
                        )
                    }
                }

                // ==========================================
                // GRUPO 3: Suave & Tranquilo
                // ==========================================
                SoundListItem(
                    title = "Suave & Tranquilo",
                    icon = if (isSuaveExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isSuaveExpanded = !isSuaveExpanded }
                )

                AnimatedVisibility(visible = isSuaveExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {

                        SoundListItem(
                            title = "Guitarra Relaxante",
                            iconDrawable = if (isPlaying && somAtual == R.raw.guitarra_relaxante) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.guitarra_relaxante) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.guitarra_relaxante) }
                        )

                        SoundListItem(
                            title = "Romântica & Relaxante",
                            iconDrawable = if (isPlaying && somAtual == R.raw.romantica_relaxante) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.romantica_relaxante) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.romantica_relaxante) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // ==========================================
                // BOTÃO DE TIMER (Aparece ao dar Play)
                // ==========================================
                AnimatedVisibility(visible = isPlaying) {
                    Button(
                        onClick = { iniciarTimer(30) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (timerAtivo) Color.Gray else IconGreenColor
                        ),
                        shape = RoundedCornerShape(50)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ThumbUp,
                            contentDescription = "Timer",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(if (timerAtivo) "Dormir em 30 min (Ativo)" else "Ativar Timer (30 min)")
                    }
                }
            }
        }
    }
}

// ==========================================
// COMPONENTE ATUALIZADO (Aceita ImageVector OU Drawable)
// ==========================================
@Composable
fun SoundListItem(
    title: String,
    icon: ImageVector? = null,
    iconDrawable: Int? = null,
    iconTint: Color,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = PrimaryTextColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.weight(1f))

        // A lógica que decide qual imagem carregar
        if (iconDrawable != null) {
            Icon(
                painter = painterResource(id = iconDrawable),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        } else if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}