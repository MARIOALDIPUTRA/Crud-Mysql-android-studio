<?php
$localhost = "127.0.0.1";
$username = "root";
$password = "";
$database = "mobile_database";

$koneksi = mysqli_connect($localhost, $username, $password, $database);

if (!$koneksi) {
    echo "gagal";
}
