<?php
session_start(); // Inicia la sesión

// Si el usuario ya ha iniciado sesión, mostrar mensaje de bienvenida
if (isset($_SESSION['username'])) {
    echo "BIENVENIDO " . htmlspecialchars($_SESSION['username']) . " | <a href='?logout=true'>Cerrar sesión</a>";

    // Manejo del cierre de sesión
    if (isset($_GET['logout'])) {
        session_destroy(); // Destruir la sesión
        $_SESSION = []; // Vaciar la variable de sesión
        header("Refresh:0"); // Recargar la página sin redirigir
        exit;
    }
} else {
    // Si el formulario fue enviado, procesar login sin redirigir
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $input_user = $_POST['username'] ?? ''; // Obtener usuario ingresado
        $input_pass = $_POST['password'] ?? ''; // Obtener contraseña ingresada

        // Leer el archivo JSON con los usuarios
        $json_data = file_get_contents("usuarios.json");
        if ($json_data === false) {
            die("Error al leer el archivo JSON.");
        }

        // Decodificar el JSON a un array PHP
        $usuarios = json_decode($json_data, true);
        if ($usuarios === null) {
            die("Error al decodificar el JSON.");
        }

        // Verificar usuario y contraseña
        foreach ($usuarios['usuarios'] as $usuario) {
            if ($usuario['username'] === $input_user && password_verify($input_pass, $usuario['password'])) {
                $_SESSION['username'] = $input_user; // Guardar usuario en sesión
                header("Refresh:0"); // Recargar la página sin redirigir
                exit;
            }
        }

        // Si no se encontró una coincidencia
        echo "<p style='color: red;'>Usuario o contraseña incorrectos.</p>";
    }

    // Mostrar formulario de inicio de sesión si no está autenticado
    ?>
    <h2>Iniciar sesión</h2>
    <form method="POST">
        <input type="text" name="username" placeholder="Usuario" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <button type="submit">Iniciar sesión</button>
    </form>
    <?php
}
?>
