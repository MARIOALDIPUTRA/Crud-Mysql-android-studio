<?php

include 'koneksi.php';

$nama = $_POST['nama'];
$nim = $_POST['nim'];
$no_telp = $_POST['no_telp'];
$email = $_POST['email'];

$tambah_query = "INSERT INTO tb_mobile(nama,nim,no_telp,email) VALUES('$nama','$nim','$no_telp','$email')";

$result = mysqli_query($koneksi, $tambah_query);

if ($result) {
    echo "Data Input";
} else {
    echo "Failed";
}

mysqli_close($koneksi);
