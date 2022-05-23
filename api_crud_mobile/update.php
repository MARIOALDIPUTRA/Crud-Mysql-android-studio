<?php
include 'koneksi.php';

$id = $_POST['id'];
$nama = $_POST['nama'];
$nim = $_POST['nim'];
$no_telp = $_POST['no_telp'];
$email = $_POST['email'];

$update_data = "UPDATE tb_mobile SET nama = '$nama', nim = '$nim', no_telp = '$no_telp', email = '$email' 
WHERE id = '$id' ";

$result = mysqli_query($koneksi, $update_data);

if ($result) {
    echo "Berhasil Update";
} else {
    echo "Failed";
}
mysqli_close($koneksi);
