-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Des 2021 pada 14.48
-- Versi server: 10.4.16-MariaDB
-- Versi PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_library`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `kd_admin` varchar(20) NOT NULL,
  `nama_admin` varchar(50) NOT NULL,
  `id_role` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `nama_image` text NOT NULL,
  `image_path` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `kd_admin`, `nama_admin`, `id_role`, `username`, `password`, `jabatan`, `nama_image`, `image_path`) VALUES
(8, 'ADM002', 'Budi', 1, 'Budi', 'budi123', 'Dosen', 'UIN-Malang.jpg', 'C:UsersLENOVOPicturesUIN-Malang.jpg'),
(9, 'ADM003', 'Sintia Anjasari', 1, 'sintia', 'sintia123', 'Staff', 'UIN-Malang.jpg', 'C:UsersLENOVOPicturesUIN-Malang.jpg'),
(10, 'ADM004', 'Dito Ardila', 1, 'dito', 'dito123', 'Dosen', '', ''),
(11, 'ADM005', 'Alya Saja', 1, 'ali', 'ali123', 'Staff', '', ''),
(14, 'ADM007', 'Riza', 1, 'riza', 'riza123', 'Dosen', '', ''),
(16, 'ADM008', 'budi', 1, 'budi', 'budi123', 'dosen', '', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `id_buku` int(11) NOT NULL,
  `kode_buku` varchar(50) NOT NULL,
  `judul_buku` varchar(50) NOT NULL,
  `penulis` varchar(50) NOT NULL,
  `penerbit` varchar(25) NOT NULL,
  `thn_terbit` varchar(25) NOT NULL,
  `kategori` varchar(25) NOT NULL,
  `status` varchar(25) NOT NULL,
  `nama_image` text NOT NULL,
  `image_path` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`id_buku`, `kode_buku`, `judul_buku`, `penulis`, `penerbit`, `thn_terbit`, `kategori`, `status`, `nama_image`, `image_path`) VALUES
(11, 'BOK001', 'PHP FOR KIDS', 'Mark Zu', 'Los Angles', '2010', 'Sains', 'belum_pinjam', 'UIN-Malang.jpg', 'C:UsersLENOVOPicturesUIN-Malang.jpg'),
(12, 'BOK002', 'Web Dasar', 'Abdurrozaaq', 'Pustaka Media', '2012', 'Sains', 'belum_pinjam', 'UIN-Malang.jpg', 'C:UsersLENOVOPicturesUIN-Malang.jpg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_peminjaman`
--

CREATE TABLE `detail_peminjaman` (
  `kd_pinjam` varchar(50) NOT NULL,
  `kd_buku` varchar(50) NOT NULL,
  `judul_buku` varchar(50) NOT NULL,
  `penulis` varchar(50) NOT NULL,
  `jml_pinjam` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id_mahasiswa` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  `kd_mhs` varchar(20) NOT NULL,
  `nama_mhs` varchar(50) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fakulitas` varchar(25) NOT NULL,
  `jurusan` varchar(25) NOT NULL,
  `nama_image` text NOT NULL,
  `image_path` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`id_mahasiswa`, `id_role`, `kd_mhs`, `nama_mhs`, `username`, `password`, `fakulitas`, `jurusan`, `nama_image`, `image_path`) VALUES
(7, 2, 'MHS003', 'Abdurrozzaaq Ashshiddiqi Zuhri', 'rozaq', 'diki', 'Sainteks', 'TI', 'UIN-Malang.jpg', 'C:UsersLENOVOPicturesUIN-Malang.jpg'),
(8, 2, 'MHS004', 'DIki', 'diki', 'diki123', 'Saintek', 'TI', '', ''),
(9, 2, 'MHS005', 'Budi Andara', 'budi', 'budi123', 'SosHum', 'IPS', '', ''),
(13, 2, 'MHS006', 'Gogot', 'gogot', 'gogot123', 'Saintek', 'TI', '', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int(11) NOT NULL,
  `kd_mhs` varchar(20) NOT NULL,
  `kd_peminjaman` varchar(20) NOT NULL,
  `tgl_pinjam` varchar(25) NOT NULL,
  `tgl_kembali` varchar(25) NOT NULL,
  `jml_pinjam` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id_pengembalian` int(11) NOT NULL,
  `kd_pengembalian` varchar(20) NOT NULL,
  `kd_pinjam` varchar(50) NOT NULL,
  `kode_mhs` varchar(50) NOT NULL,
  `tgl_pinjam` varchar(50) NOT NULL,
  `tgl_kembali` varchar(50) NOT NULL,
  `jml_pinjam` varchar(20) NOT NULL,
  `keterlambatan` varchar(25) NOT NULL,
  `denda` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`id_pengembalian`, `kd_pengembalian`, `kd_pinjam`, `kode_mhs`, `tgl_pinjam`, `tgl_kembali`, `jml_pinjam`, `keterlambatan`, `denda`) VALUES
(15, 'PGM001', 'PJM001', 'MHS005', '10-06-2021', '17-06-2021', '3', '0', '0'),
(16, 'PGM002', 'PJM002', 'MHS003', '10-06-2021', '17-06-2021', '4', '3', '9000'),
(17, 'PGM003', 'PJM003', 'MHS003', '10-06-2021', '17-06-2021', '1', '0', '0'),
(18, 'PGM004', 'PJM004', 'MHS006', '10-06-2021', '17-06-2021', '2', '1', '3000'),
(19, 'PGM005', 'PJM005', 'MHS003', '10-06-2021', '17-06-2021', '2', '1', '3000'),
(20, 'PGM006', 'PJM006', 'MHS004', '10-06-2021', '17-06-2021', '2', '0', '0'),
(21, 'PGM007', 'PJM007', 'MHS005', '02-12-2021', '09-12-2021', '3', '1', '3000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `nama_role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `role`
--

INSERT INTO `role` (`id_role`, `nama_role`) VALUES
(1, 'Admin'),
(2, 'Mahasiswa');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id_mahasiswa`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`);

--
-- Indeks untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id_pengembalian`);

--
-- Indeks untuk tabel `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `buku`
--
ALTER TABLE `buku`
  MODIFY `id_buku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id_mahasiswa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `id_pengembalian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT untuk tabel `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
