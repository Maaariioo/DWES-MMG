<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<h1>Tienda Frutas Mario</h1>
<form method="post">
<?php

$frutas = array("Manzana", "Plátano", "Pera", "Naranja", "Uva");

$total_productos = count($frutas);

for ($i=0; $i < $total_productos ; $i++) { 
    
    echo '<label>Producto '. ($i + 1). ':</label>';
    echo '<select name="producto'.($i + 1).'">';
    
        foreach ($frutas as $fruta) {
            
            echo '<option value="'. $fruta .'">'.$fruta . '</option>';

        }
    
    echo '</select>';
    echo '<input type="number" min="1" max="10" name="cantidad' . ($i +1) . '"><br>';

}

?>
<input type="checkbox" name="perejil" value="si">
<label>¿Añadir Perejil?</label><br>
<input type="submit" name="comprar" value="Comprar">

</form>

<?php

    if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['comprar'])){


        echo '<h3>Ticket:</h3>';

        echo '<ul>';

        for ($i = 0;$i< $total_productos;$i++){

            echo '<li>'. $_POST['cantidad'.($i+1)] . ' - ' . $_POST['producto'.($i + 1)] .'</li>';

        }

        echo '</ul>';

        if($_POST['perejil'] == "si"){

            echo "*Añadido perejil*";

        }

    }
?>
</body>
</html>
