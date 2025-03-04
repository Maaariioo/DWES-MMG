<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adivina un número</title>
</head>
<body>

    <form action="" method="post">
        <label for="num">Número:</label>
        <input type="number" name="num" placeholder="Intenta adivinar mi número" value="<?php echo isset($_POST['num']) ? $_POST['num'] : ''; ?>" required min="1" max="10">
        <br>
        <input type="submit" value="Comprobar">
    </form>

    <?php
    if (!isset($_SESSION['aleatorio'])) {
        $_SESSION['aleatorio'] = rand(1, 10);
        echo "<p>He elegido un número aleatorio, intenta adivinarlo.</p>";
    }

    $aleatorio = $_SESSION['aleatorio'];

    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['num'])) {
        $num = intval($_POST['num']);

        if ($num < 1 || $num > 10) {
            echo "<p>Por favor, ingresa un número entre 1 y 10.</p>";
        } else {
            calcular($num, $aleatorio);
        }
    }

    function calcular($num, $aleatorio) {
        if ($num < $aleatorio) {
            echo "<p>Mi número es mayor.</p>";
        } elseif ($num > $aleatorio) {
            echo "<p>Mi número es menor.</p>";
        } else {
            echo "<p>¡HAS ACERTADO! Reiniciando el juego...</p>";
            session_destroy();
            echo "<script>setTimeout(() => { window.location.reload(); }, 2000);</script>";
        }
    }

    ?>
</body>
</html>
