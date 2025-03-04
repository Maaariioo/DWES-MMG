<?php
// Iniciar la sesión
session_start();

// Lista de animales
$animales = ["gato", "perro", "elefante", "tigre", "leon", "jirafa", "cebra", "lobo"];

// Inicializar variables de sesión si no están definidas (primer acceso)
if (!isset($_SESSION['intentos']) || isset($_POST['Reiniciar'])) {
    $_SESSION['intentos'] = 10; // Número de intentos
    $_SESSION['animal'] = $animales[array_rand($animales)]; // Seleccionar un animal aleatorio
    $_SESSION['letras_ingresadas'] = []; // Letras que el usuario ha ingresado
}

// Convertir la palabra en un array de letras
$letras = str_split($_SESSION['animal']);

// Variables de control
$error_letra = false; // Indica si la letra ingresada no es válida

// Procesar la letra ingresada por el usuario
if (isset($_POST['letra']) && $_POST['accion'] == 'Jugar') {
    $letra_ingresada = strtolower($_POST['letra']); // Convertir a minúscula

    // Validar que la letra sea válida (a-z)
    if (preg_match("/^[a-z]$/", $letra_ingresada)) {
        // Verificar si la letra ya fue ingresada
        if (!in_array($letra_ingresada, $_SESSION['letras_ingresadas'])) {
            $_SESSION['letras_ingresadas'][] = $letra_ingresada; // Agregar a las letras ingresadas
            // Reducir intentos solo si la letra no está en la palabra
            if (!in_array($letra_ingresada, $letras)) {
                $_SESSION['intentos']--;
            }
        }
    } else {
        $error_letra = true; // Marcar error si no es válida
    }
}

// Obtener la longitud de la palabra
$longitud = strlen($_SESSION['animal']);

// Verificar si el usuario ha ganado
$ganado = true;
foreach ($letras as $letra) {
    if (!in_array($letra, $_SESSION['letras_ingresadas'])) {
        $ganado = false;
        break;
    }
}

// Verificar si el usuario ha perdido
$perdido = ($_SESSION['intentos'] <= 0);
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Juego del Ahorcado</title>
</head>
<body>
    <h1>Juego del Ahorcado</h1>

    <!-- Mostrar la palabra oculta con guiones y letras adivinadas -->
    <p>
        <?php
        for ($i = 0; $i < $longitud; $i++) {
            $letra = $letras[$i];
            if (in_array($letra, $_SESSION['letras_ingresadas'])) {
                echo $letra . " "; // Mostrar letra adivinada
            } else {
                echo "_ "; // Mostrar guión para letra no adivinada
            }
        }
        ?>
    </p>

    <!-- Mostrar intentos restantes -->
    <p>Intentos restantes: <?php echo $_SESSION['intentos']; ?></p>

    <!-- Mostrar letras ingresadas -->
    <p>Letras ingresadas: <?php echo implode(", ", $_SESSION['letras_ingresadas']); ?></p>

    <!-- Mostrar mensajes de error o fin del juego -->
    <?php if ($error_letra) { echo "<p>Letra inválida</p>"; } ?>
    <?php if ($ganado) { echo "<p>¡Ganaste! Has adivinado el animal.</p>"; } ?>
    <?php if ($perdido) { echo "<p>¡Perdiste! El animal era: " . $_SESSION['animal'] . "</p>"; } ?>

    <!-- Formulario para ingresar una letra -->
    <form method="POST">
        <label for="letra">Letra:</label>
        <input maxlength="1" size="1" type="text" id="letra" name="letra"/>
        <br><br>
        <!-- Botones para enviar la letra o reiniciar el juego -->
        <?php if (!$ganado && !$perdido) { ?>
            <input type="submit" name="accion" value="Jugar" />
        <?php } ?>
        <input type="submit" name="accion" value="Reiniciar" />
    </form>
</body>
</html>