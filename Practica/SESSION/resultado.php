<?php session_start(); ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado</title>
</head>
<body>

<?php

$nombre = $_SESSION['nombre'] ?? '';
$edad = $_SESSION['edad'] ?? '';
$hobbies = $_SESSION['hobbies'] ?? [];

if ($nombre && $edad) {
    echo "<p>Nombre: " . $nombre . "</p>";
    echo "<p>Edad: " . $edad . "</p>";

    echo "<p>Hobbies:</p>";
    echo "<ul>";
    foreach ($hobbies as $hobby) {
        echo "<li>" . $hobby . "</li>";
    }
    echo "</ul>";
} else {
    echo "<p>No se han enviado datos.</p>";
}

?>

</body>
</html>
