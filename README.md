# 🎧 Serenidade em Sons

**Serenidade em Sons** é um aplicativo Android focado em **bem-estar, relaxamento e produtividade**, oferecendo uma coleção de sons contínuos como ruídos brancos, sons da natureza e melodias suaves.

O objetivo é ajudar o usuário a:

* Dormir melhor 😴
* Focar nos estudos 📚
* Meditar 🧘

O projeto foi desenvolvido utilizando **Kotlin + Jetpack Compose**, seguindo práticas modernas do desenvolvimento Android.

## ✨ Funcionalidades

### 🎯 Categorias Focadas

Organização dos sons por intenção de uso:

* Dormir
* Estudar
* Meditar

### 🔊 Player de Áudio Integrado

* Reprodução contínua (loop infinito)
* Utilização do `MediaPlayer` nativo do Android

### ⏯️ Controles Simples

* Botões dinâmicos de Play/Pause
* Feedback visual baseado no estado do áudio

### 📂 Listas Expansíveis (Accordion)

* Interface limpa e organizada
* Uso de `AnimatedVisibility` para animações suaves

### ⏳ Sleep Timer

* Permite programar o desligamento automático
* Ideal para uso antes de dormir

### 🌗 Tema Claro/Escuro

* Adaptação automática ao sistema do usuário
* Implementado com `MaterialTheme (Material 3)`

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Design System:** Material Design 3 (MD3)
* **Navegação:** Navigation Compose
* **Áudio:** `android.media.MediaPlayer`
* **Assincronismo:** Kotlin Coroutines
* **Gerenciamento de Estado:** `remember`, `mutableStateOf`

## 📱 Estrutura do App

### 🏠 HomeScreen

Tela inicial com:

* Boas-vindas
* Navegação para categorias

### 🎧 Sound Screens

(Telas como `DormirScreen`, `EstudarScreen`, etc)

* Lista de sons organizados
* Controle individual de reprodução
* Ativação do Timer

### 🔽 Bottom Navigation

* Navegação rápida entre as principais seções do app

## 🚀 Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/SEU_USUARIO/SerenidadeEmSons.git

### 2. Abra no Android Studio

* File → Open → selecione o projeto

### ⚠️ Importante: Arquivos de Áudio

Por questões de tamanho e direitos autorais, os arquivos `.mp3` não estão no repositório.

Para rodar o app:
2. Adicione seus áudios:
   
chuva_leve.mp3
som_floresta.mp3
ruido_branco.mp3

📌 Regras:

* Apenas letras minúsculas
* Sem espaços
* Use `_` (underline)

### 3. Execute o App

* Emulador ou dispositivo físico
* Android 6.0+
  
## 🔮 Próximos Passos (Backlog)

* [ ] Tela de Configurações completa
* [ ] Reprodução em segundo plano (Background Service / MediaSession)
* [ ] Escolha personalizada do Timer (Dialog: 15, 30, 45, 60 min)
* [ ] Favoritar sons ❤️
* [ ] Mix de sons (ex: chuva + vento)

---

## 🎯 Objetivo do Projeto

Este projeto demonstra habilidades em:

* Desenvolvimento Android moderno
* UI declarativa com Compose
* Gerenciamento de estado
* Manipulação de mídia
* Experiência do usuário (UX/UI)
  
## 👨‍💻 Autor
**Gabryel Rodrigues**

## ⭐ Contribuições
Sinta-se à vontade para:

* Abrir issues
* Sugerir melhorias
* Contribuir com o projeto

## 💙 Agradecimento

Desenvolvido com dedicação utilizando **Kotlin + Jetpack Compose**
