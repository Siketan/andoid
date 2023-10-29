# andoid

# Key Store
## alias = siketan
## password = Ngawikab

## Struktur Folder
```
android
├── app
│   ├── src
│   │   ├── main
│   │   ├── java.id.go.ngawikab.siketan
│   │   │   ├── data
│   │   │   │   ├── address
│   │   │   │   ├── auth
│   │   │   │   ├── chat
│   │   │   │   └── farm
│   │   │   ├── di
│   │   │   │   ├── AddressModule.kt
│   │   │   │   ├── AppModule.kt
│   │   │   │   ├── AuthModule.kt
│   │   │   │   ├── ChatModule.kt
│   │   │   │   └── FarmModule.kt
│   │   │   ├── domain
│   │   │   │   ├── address
│   │   │   │   ├── auth
│   │   │   │   ├── chat
│   │   │   │   └── farm
│   │   │   ├── presentation
│   │   │   ├── utils
│   │   │   └── App.kt
│   │   ├── res
│   │   │   ├── drawable
│   │   │   ├── layout
│   │   │   ├── mipmap
│   │   │   └── values
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── build.gradle
```


## Data
Data layer adalah tempat untuk mendapatkan data dari API yang kemudian diolah dan dimasukan kedalam


## Domain
Domaian layer adalah folder untuk mengelola bisnis logic dan manipulasi data yang didapatkan dari data layer.

## Presentation
Folder ini ditujukan untuk menampung semua ui (dessign) yang terdapat didalam aplikasi

## DI
Folder ini digunakan untuk melakukan injection pada semua class yang terdapat didalam project

## Utils
Folder ini dibuat dengan tujuan mengahandle pem-formatan tanggal, pembuatan custom extension hingga custom ui yang dibutuhkan selama proses pengembangan aplikasi