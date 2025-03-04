<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tabla de Multiplicar</title>
</head>
<body>

    <form action="" method="post">

        <label for="num">Numero:</label>
        <input type="number" name="num" id="">

    </form>

</body>
</html>

<?php

    $num = $_POST['num'];

    if (isset($num)){

    echo "<br><table border solid 1px black>
            <tr>
            
                <th>Operacion</th>
                <th>Resultado</th>";
    
    for ($i=0; $i <= 25 ; $i++) { 

        $resultado = $i * $num;

        echo "<tr>
        
                <td>" . $num . " x ". $i . "</td>
                <td>" . $resultado . "</td>" . "</tr>";
    }
}

?>