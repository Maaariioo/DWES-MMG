<?php
 $bebidas = array('Coca-Cola', 'Agua', 'Cerveza');
 $MAX_BEBIDAS = 3;
 $TOPE_BEBIDAS = 10;
 ?>

<!DOCTYPE html>
<html>
<head>
    <title>Carrito de la compra de bebidas</title>
</head>
<body>
    <h1>Carrito de la compra de bebidas</h1>
    <form method="post">
     
        <p>Selecciona tus bebidas:</p>

        <?php 
        
            for ($i=1; $i <= $MAX_BEBIDAS ; $i++) { 

                echo "<label for\"bebida$i\">Bebida ". $i."</label>";
                echo "<select name=\"bebida$i\">";

                    for ($j=0; $j < count($bebidas) ; $j++) { 
                        
                        echo "<option value=\"$bebidas[$j]\">".$bebidas[$j]."</option>";

                    } 

                echo "</select>";

                echo "<input type=\"number\" name=\"cantidad$i\" max=\"$TOPE_BEBIDAS\">";
                echo "<br>";
            }
        
        ?>

        <label for="abridor">¿Añadir un abridor?</label>
        <input type="checkbox" id="abridor" name="abridor"><br>

        <button type="submit">Actualizar</button>

    </form>

    <?php
    
    if ($_SERVER['REQUEST_METHOD'] === 'POST'){
        
        echo "<h3>Ticket:</h3>";

        for ($i=1; $i <= $MAX_BEBIDAS; $i++) { 

            $bebida = $_POST["bebida$i"] ?? 0 ;
            $cantidad = $_POST["cantidad$i"] ?? 0;

            echo "<p>Has comprado $cantidad $bebida</p>";
            
        }
    }

    if ($_POST['abridor'] === 'on') {
        
        echo "<h3>Abridor añadido</h3>";
    }
    
    ?>
</body>
</html>