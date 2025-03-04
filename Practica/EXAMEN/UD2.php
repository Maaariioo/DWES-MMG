<?php
$productos = array('Fresas', 'Platanos', 'Limones');
$MAX_PRODUCTOS = 3;
$TOPE_PRODUCTO = 10;
?>
<html>
<head>
    <title>UD2 - Carrito simple</title>
</head>
<body>

<p>Cesta de la compra de fruta y verdura:</p>

<form method="POST">

<?php

for ($i = 1; $i <= $MAX_PRODUCTOS; $i++) {

    echo "<label for=\"producto$i\">Producto $i</label>";
    echo "<select name=\"producto$i\">";

    for ($j = 0; $j < count($productos); $j++) {

        echo "<option value=\"{$productos[$j]}\">{$productos[$j]}</option>";

    }

    echo "</select>";

    echo "<input type=\"number\" name=\"cantidad$i\" value=\"0\" min=\"0\" max=\"$TOPE_PRODUCTO\">"; 
    echo "<br>";
}
?>

<br>

<label for="perejil">¿Añadir perejil?</label>
<input type="checkbox" name="perejil" id="perejil">

<br>

<button type="submit">Actualizar</button>

</form>

<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    
    echo "<h1>Resumen</h1>";

    for ($i = 1; $i <= $MAX_PRODUCTOS; $i++) {

        $producto = $_POST["producto$i"] ?? null;
        $cantidad = $_POST["cantidad$i"] ?? 0;

        if (!empty($producto) && $cantidad > 0) {

            $cantidad = min($cantidad, $TOPE_PRODUCTO);

            echo "<h3>Has comprado $cantidad $producto</h3>";

        }
    }

    if (isset($_POST['perejil']) && $_POST['perejil'] === 'on') {

        echo "<h4>Además, has comprado perejil.</h4>";

    }
}
?>
</body>
</html>
