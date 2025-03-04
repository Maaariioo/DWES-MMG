<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de la Compra</title>
</head>
<body>

    <h1>Lista de la Compra</h1>

    <!-- Formulario para seleccionar los productos -->
    <form method="POST">
        <label for="productos">Selecciona los productos:</label><br>
        <select name="productos[]" id="productos" multiple>
            <!-- Las opciones de productos se generarán desde PHP -->
            <?php
            // Array de productos
            $productos = ["Manzana", "Coco", "Plátano"];

            // Iterar sobre el array de productos y generar las opciones
            foreach ($productos as $producto) {
                echo "<option value='$producto'>$producto</option>";
            }
            ?>
        </select><br><br>

        <!-- Checkbox para añadir Perejil -->
        <input type="checkbox" name="perejil" id="perejil">
        <label for="perejil">Añadir Perejil</label><br><br>

        <button type="submit">Añadir al ticket</button>
    </form>

    <!-- Mostrar el ticket de compra -->
    <div class="ticket">
        <h3>Ticket de Compra:</h3>
        <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            // Procesar los productos seleccionados
            if (isset($_POST['productos'])) {
                foreach ($_POST['productos'] as $producto) {
                    echo "<p>- $producto</p>";
                }
            }

            // Verificar si el checkbox de Perejil está marcado
            if (isset($_POST['perejil'])) {
                echo "<p>- Perejil</p>";
            }
        }
        ?>
    </div>

</body>
</html>
