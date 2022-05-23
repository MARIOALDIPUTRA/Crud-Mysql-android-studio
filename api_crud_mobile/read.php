<?php
include 'koneksi.php';

$result = array();
$result['tb_mobile'] = array();
$read_data = "SELECT * from tb_mobile";

$response = mysqli_query($koneksi, $read_data);

while ($row = mysqli_fetch_array($response)) {
    $index['id']      = $row['0'];
    $index['nama']    = $row['1'];
    $index['nim']   = $row['2'];
    $index['no_telp'] = $row['3'];
    $index['email'] = $row['4'];

    array_push($result['tb_mobile'], $index);
}

$result["success"] = "1";
echo json_encode($result);
mysqli_close($koneksi);
