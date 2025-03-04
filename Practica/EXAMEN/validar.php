<?php
// Inicializamos las variables para almacenar los errores
$errores = array();
$nombre = $correo = $edad = $genero = '';

// Validación del formulario cuando se envía
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    // Validación del nombre (solo letras y espacios)
    if (empty($_POST['nombre'])) {
        $errores['nombre'] = 'El nombre es obligatorio.';
    } else {
        $nombre = $_POST['nombre'];
        if (!preg_match("/^[a-zA-Z ]*$/", $nombre)) {
            $errores['nombre'] = 'Solo se permiten letras y espacios en el nombre.';
        }
    }

    // Validación del correo electrónico
    if (empty($_POST['correo'])) {
        $errores['correo'] = 'El correo es obligatorio.';
    } else {
        $correo = $_POST['correo'];
        if (!filter_var($correo, FILTER_VALIDATE_EMAIL)) {
            $errores['correo'] = 'El correo electrónico no es válido.';
        }
    }

    // Validación de la edad (debe ser mayor que 18)
    if (empty($_POST['edad'])) {
        $errores['edad'] = 'La edad es obligatoria.';
    } else {
        $edad = $_POST['edad'];
        if (!is_numeric($edad) || $edad < 18) {
            $errores['edad'] = 'Debe tener al menos 18 años.';
        }
    }

    // Validación del género
    if (empty($_POST['genero'])) {
        $errores['genero'] = 'El género es obligatorio.';
    } else {
        $genero = $_POST['genero'];
    }

    // Si no hay errores, podemos procesar los datos (en este caso, simplemente mostrar un mensaje de éxito)
    if (empty($errores)) {
        echo '<p style="color:green;">Formulario enviado exitosamente.</p>';
    }
}
?>

<!-- Formulario HTML -->
<form method="POST" action="">
    <p>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" value="<?php echo htmlspecialchars($nombre); ?>" />
        <?php if (isset($errores['nombre'])): ?>
            <div style="color: red;"><?php echo $errores['nombre']; ?></div>
        <?php endif; ?>
    </p>

    <p>
        <label for="correo">Correo electrónico:</label>
        <input type="text" name="correo" value="<?php echo htmlspecialchars($correo); ?>" />
        <?php if (isset($errores['correo'])): ?>
            <div style="color: red;"><?php echo $errores['correo']; ?></div>
        <?php endif; ?>
    </p>

    <p>
        <label for="edad">Edad:</label>
        <input type="text" name="edad" value="<?php echo htmlspecialchars($edad); ?>" />
        <?php if (isset($errores['edad'])): ?>
            <div style="color: red;"><?php echo $errores['edad']; ?></div>
        <?php endif; ?>
    </p>

    <p>
        <label for="genero">Género:</label>
        <select name="genero">
            <option value="">Seleccione...</option>
            <option value="masculino" <?php echo ($genero == 'masculino') ? 'selected' : ''; ?>>Masculino</option>
            <option value="femenino" <?php echo ($genero == 'femenino') ? 'selected' : ''; ?>>Femenino</option>
        </select>
        <?php if (isset($errores['genero'])): ?>
            <div style="color: red;"><?php echo $errores['genero']; ?></div>
        <?php endif; ?>
    </p>

    <p>
        <input type="submit" value="Enviar" />
    </p>
</form>
