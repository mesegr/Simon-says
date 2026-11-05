# Simon Says Classic

Juego de memoria para Android: la app ilumina una secuencia de colores y tienes que repetirla en el mismo orden. Cada ronda añade un color más.

<p align="center">
  <img src="img/simon1.jpg" width="280" alt="Partida en curso de Simon Says Classic">
</p>

## Funciones

- Secuencias cada vez más largas, una ronda tras otra
- Contador de puntos de la partida y récord personal
- El récord se guarda en el dispositivo
- Interfaz sencilla, adaptada a distintos tamaños de pantalla

## Descarga

[Simon Says Classic 1.0.1 (APK)](https://github.com/mesegr/Simon-says/releases/latest/download/Download.Simon.Says.apk)

Requiere Android 7.0 o superior. Al instalarlo, Android puede pedirte permiso para instalar apps de orígenes desconocidos. El resto de versiones están en [Releases](https://github.com/mesegr/Simon-says/releases).

## Tecnologías

- Kotlin y Jetpack Compose
- Arquitectura MVVM: el estado del juego vive en el `ViewModel` y se expone con `StateFlow`; la interfaz lo observa con `collectAsState`
- Corrutinas para animar la secuencia de colores
- `SharedPreferences` para guardar el récord
- minSdk 24, targetSdk 35

## Estructura

```
SimonSays/app/src/main/java/com/example/simonahorasidice/
├── MainActivity.kt          # Punto de entrada y carga del récord
├── model/                   # Estados del juego y colores
├── modelview/MyViewModel.kt # Lógica del juego y estado
├── view/UI.kt               # Pantallas en Jetpack Compose
└── ui/theme/                # Tema de la app
```

## Cómo compilar

1. Instala una versión reciente de Android Studio.
2. Clona el repo: `git clone https://github.com/mesegr/Simon-says.git`
3. Abre la carpeta `SimonSays` con Android Studio y espera a que Gradle sincronice.
4. Pulsa **Run** con un emulador o un móvil conectado.

## Errores y sugerencias

Abre una [issue](https://github.com/mesegr/Simon-says/issues) indicando qué pasó y cómo reproducirlo.
