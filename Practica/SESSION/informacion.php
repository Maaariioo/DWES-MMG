<?php
session_start();


if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $_SESSION['nombre'] = $_POST['nombre'];
    $_SESSION['edad'] = $_POST['edad'];
    $_SESSION['hobbies'] = isset($_POST['hobby']) ? $_POST['hobby'] : [];
    header('Location: resultado.php');
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informacion</title>
</head>
<body>

<form action="informacion.php" method="post">
    <label for="nombre">Nombre:</label>
    <input type="text" name="nombre" id=""><br>

    <label for="edad">Edad:</label>
    <input type="text" name="edad" id=""><br>

    <label for="hobby">Hobbies:</label><br>
    <input type="checkbox" name="hobby[]" value="leer">
    <label for="hobby">Leer</label><br>

    <input type="checkbox" name="hobby[]" value="viajar">
    <label for="hobby">Viajar</label><br>

    <input type="checkbox" name="hobby[]" value="dibujar">
    <label for="hobby">Dibujar</label><br>

    <input type="submit" value="Enviar">
</form>

</body>
</html>
