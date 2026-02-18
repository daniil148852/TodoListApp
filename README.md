# Todo List Android App

A simple todo list application for Android.

## Features

- Add new tasks
- Mark tasks as completed (tap on task)
- Delete tasks
- Clean and simple UI

## Build

To build the APK locally:

```bash
./gradlew assembleDebug
```

The APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`

## GitHub Actions

This project uses GitHub Actions to automatically build the APK on every push or pull request. The APK artifact will be available in the Actions tab after a successful build.

## Tech Stack

- Kotlin
- AndroidX
- Material Design Components
- RecyclerView
