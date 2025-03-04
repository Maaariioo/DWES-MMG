<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calcular Area de Triangulo</title>
</head>
<body>
    
    <form action="" method="post">

        <label for="base">Base:</label>
        <input type="number" name="base">
        <br>
        <label for="altura">Altura:</label>
        <input type="number" name="altura">
        <br>
        <input type="submit" value="Calcular area">

    </form>

    <?php 
    
        $base = $_POST['base'];
        $altura = $_POST['altura'];
        $area = $base * $altura / 2;

        echo "<p>El área del triángulo es: " . number_format($area, 2) . " cm²</p>";

    ?>

</body>
</html>