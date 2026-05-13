package com.example.serinidadedesons.Screens

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import com.example.serinidadedesons.R

@Composable
fun MeditarScreen(navController: NavController) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // ==========================================
    // ESTADOS DO ÁUDIO
    // ==========================================
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var somAtual by remember { mutableStateOf<Int?>(null) }

    // ==========================================
    // ESTADOS DOS MENUS SANFONA
    // ==========================================
    var isGuiadaExpanded by remember { mutableStateOf(false) }
    var isNaturezaExpanded by remember { mutableStateOf(false) }
    var isMusicaExpanded by remember { mutableStateOf(false) }

    // ==========================================
    // LÓGICA DE ÁUDIO
    // ==========================================
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

            // Botão de Voltar
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
                // Imagem Principal - Formato Pílula
                Image(
                    painter = painterResource(id = R.drawable.meditar_serenidade),
                    contentDescription = "Imagem de pessoa meditando",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(130.dp)
                        .clip(RoundedCornerShape(100.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Logo Menor
                Image(
                    painter = painterResource(id = R.drawable.logo_serenidade),
                    contentDescription = "Logo Serenidade",
                    modifier = Modifier.size(230.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))


                // GRUPO 1: Meditação Guiada
                SoundListItem(
                    title = "Meditação Guiada",
                    icon = if (isGuiadaExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isGuiadaExpanded = !isGuiadaExpanded }
                )

                AnimatedVisibility(visible = isGuiadaExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        SoundListItem(
                            title = "Respiração Consciente",
                            iconDrawable = if (isPlaying && somAtual == R.raw.respiracao) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.respiracao) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.respiracao) }
                        )
                    }
                }
                // GRUPO 2: Sons de água
                SoundListItem(
                    title = "Água em Movimento",
                    icon = if (isNaturezaExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isNaturezaExpanded = !isNaturezaExpanded }
                )

                AnimatedVisibility(visible = isNaturezaExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        SoundListItem(
                            title = "Água Corrente",
                            iconDrawable = if (isPlaying && somAtual == R.raw.agua_corrente) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.agua_corrente) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.agua_corrente) }
                        )
                        SoundListItem(
                            title = "Oceano (Mar)",
                            iconDrawable = if (isPlaying && somAtual == R.raw.mar_calmo) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.mar_calmo) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.mar_calmo) }
                        )
                    }
                }

                // GRUPO 3: Passáros cantando
                SoundListItem(
                    title = "Passaros",
                    icon = if (isMusicaExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isMusicaExpanded = !isMusicaExpanded }
                )

                AnimatedVisibility(visible = isMusicaExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        SoundListItem(
                            title = "PicaPau Floresta",
                            iconDrawable = if (isPlaying && somAtual == R.raw.passaro_cantando) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.passaro_cantando) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.passaro_cantando) }
                        )
                        SoundListItem(
                            title = "Sabia Floresta",
                            iconDrawable = if (isPlaying && somAtual == R.raw.sabia_cantando) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.sabia_cantando) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.sabia_cantando) }
                        )
                    }
                }
                
                // Espaçador final
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
