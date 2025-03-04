<!DOCTYPE html>
<html lang="es">
<head>
    <title>Calculadora de IMC</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>

    <form method="POST">
        <label for="peso">Peso (kg):</label>
        <input type="number" step="0.1" name="peso" placeholder="Pon tu peso en kg" required>
        <br>
        <label for="altura">Altura (cm):</label>
        <input type="number" step="1" name="altura" placeholder="Pon tu altura en cm" required>
        <br><br>
        <input type="submit" value="Calcular IMC">
    </form>

    <?php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // Obtener los valores del formulario
        $peso = isset($_POST['peso']) ? floatval($_POST['peso']) : 0;
        $altura_cm = isset($_POST['altura']) ? floatval($_POST['altura']) : 0;

        if ($peso > 0 && $altura_cm > 0) {
            // Convertir altura de cm a metros
            $altura_m = $altura_cm / 100;

            // Calcular el IMC
            $imc = $peso / ($altura_m * $altura_m);
            echo "<p>Tu IMC es: " . number_format($imc, 2) . "</p>";
        } else {
            echo "<p>Por favor, introduce valores válidos para el peso y la altura.</p>";
        }
    }
    ?>

</body>
</html>
