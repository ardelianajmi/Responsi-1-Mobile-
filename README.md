**Responsi 1 Mobile - Nottingham Forest FC**

**Nama:** Adelia Najmi Raissa  
**NIM:** H1D023007
**Shift Lama:** D  
**Shift Baru:** E  
**Klub:** Nottingham Forest FC  
**Mata Kuliah:** Pemrograman Mobile  
**Responsi 1 Praktikum Pemograman Mobile** 

---
## Deskripsi Aplikasi
Aplikasi mobile sederhana berbasis **Kotlin** yang menampilkan **profil, sejarah, pelatih, dan skuad klub sepak bola Crystal Palace FC**.  
Data diambil secara real-time dari **Football-Data.org API (v4)** dengan memanfaatkan **Retrofit** dan **Kotlin Coroutines**.

Aplikasi ini terdiri dari tiga halaman utama:
1. **Halaman Klub (Club)** → Menampilkan nama dan deskripsi klub.
2. **Halaman Coach (Pelatih)** → Menampilkan informasi pelatih (nama, tanggal lahir, negara).
3. **Halaman Team (Squad)** → Menampilkan daftar pemain berdasarkan posisi.

---
## Video Demo Aplikasi
![responsi](https://github.com/user-attachments/assets/46c726f5-2587-4b4e-95fb-86cb5332d604)

---
## Penjelasan Alur

Proses pengambilan dan penyajian data pada aplikasi Responsi 1 Mobile – Nottingham Forest FC dimulai dari pemanggilan data melalui API publik Football-Data.org menggunakan Retrofit dan Kotlin Coroutines. Ketika aplikasi dijalankan, khususnya pada MainActivity, fungsi loadTeamData() dipanggil untuk melakukan permintaan ke endpoint https://api.football-data.org/v4/, yang berisi data lengkap mengenai klub Nottingham Forest FC. Setiap permintaan jaringan dikirim secara asynchronous agar tidak menghambat kinerja antarmuka pengguna. Proses ini dijalankan pada background thread menggunakan CoroutineScope(Dispatchers.IO) dan hasilnya dikembalikan ke main thread dengan withContext(Dispatchers.Main) untuk menampilkan data ke layar.

Selama proses network call, RetrofitInstance bertanggung jawab membuat objek Retrofit tunggal yang berisi base URL dan konfigurasi GSON Converter untuk memetakan data JSON ke dalam objek Kotlin. Di sisi lain, FootballDataApi mendefinisikan endpoint getTeam(id: Int) yang memerlukan header otorisasi X-Auth-Token berisi API key agar dapat mengakses data klub secara resmi. Setelah respons berhasil diterima, data JSON tersebut secara otomatis diubah menjadi objek TeamResponse yang terdiri atas beberapa elemen penting seperti nama klub, logo (crest), pelatih (coach), serta daftar pemain (squad).

Data yang telah berhasil diterima kemudian dikelola langsung di setiap Activity. Pada MainActivity, data digunakan untuk menampilkan nama dan logo klub, serta menavigasi pengguna ke halaman lainnya. Halaman HalamanClubActivity menampilkan profil dan sejarah klub dalam bentuk teks deskriptif, sedangkan HalamanCoachActivity menampilkan informasi pelatih utama seperti nama lengkap, tanggal lahir, dan negara asal. Menariknya, tampilan detail pelatih muncul secara bertahap dengan efek fade-in setelah jeda tiga detik agar antarmuka terasa lebih dinamis. Sementara itu, halaman HalamanTeamActivity bertugas menampilkan seluruh daftar pemain menggunakan RecyclerView dengan PlayerAdapter sebagai penghubung data ke tampilan.

Setiap pemain yang diambil dari API ditampilkan dalam bentuk kartu (MaterialCardView) dengan latar belakang berbeda tergantung pada posisi mereka di lapangan. Pewarnaan kartu ini diatur di dalam fungsi onBindViewHolder() menggunakan when expression yang memeriksa nilai player.position, misalnya warna kuning untuk goalkeeper, biru untuk defender, hijau untuk midfielder, dan merah untuk forward. Proses pewarnaan ini tidak hanya memperjelas peran tiap pemain, tetapi juga membuat tampilan aplikasi lebih menarik secara visual. Dengan alur ini, aplikasi mampu mengintegrasikan seluruh komponen — mulai dari pemanggilan API, pengelolaan data, hingga penyajian hasil di layar — secara efisien dan responsif, sesuai dengan standar pengembangan aplikasi Android modern.
