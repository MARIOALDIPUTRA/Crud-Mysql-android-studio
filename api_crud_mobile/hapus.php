<?php
include 'koneksi.php';

$id = $_POST["id"];

$hapus_query = "DELETE FROM tb_mobile WHERE id='$id'";
$result = mysqli_query($koneksi, $hapus_query);

if ($result) {
    echo "Menghapus Data";
} else {
    echo "Failed";
}
mysqli_close($koneksi);
