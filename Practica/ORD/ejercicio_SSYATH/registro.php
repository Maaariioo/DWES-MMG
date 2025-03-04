<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin: 50px; }
        .container { width: 300px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; background-color: #f9f9f9; }
        input, button { width: 100%; margin: 5px 0; padding: 10px; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Registro de Usuario</h2>
        <form action="registro.php" method="POST">
            <input type="text" name="nombre" placeholder="Nombre">
            <input type="email" name="email" placeholder="Correo electrónico">
            <input type="password" name="password" placeholder="Contraseña">
            <button type="submit">Registrarse</button>
        </form>
        <a href="index.php">Volver al inicio</a>
        <a href="login.php">¿Ya tiene una cuentas?</a>
    </div>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // Obtener los datos del formulario
        $nombre = $_POST['nombre'];
        $email = $_POST['email'];
        $pass = $_POST['password'];

        // Validar que los campos no estén vacíos
        if (empty($nombre) || empty($email) || empty($pass)) {
            die("<p class='error'>Todos los campos son obligatorios.</p>");
        }

        // Hashear la contraseña
        $passHasheada = password_hash($pass, PASSWORD_DEFAULT);

        // Cargar los usuarios existentes o inicializar un array vacío
        $usuarios = [];
        if (file_exists("usuarios.json")) {
            $usuarios = json_decode(file_get_contents("usuarios.json"), true);
        }

        // Verificar si el correo ya está registrado
        foreach ($usuarios as $usuario) {
            if ($usuario['email'] === $email) {
                die("<p class='error'>El correo electrónico ya está registrado.</p>");
            }
        }

        // Crear un nuevo usuario
        $nuevoUsuario = [
            "nombre" => $nombre,
            "email" => $email,
            "password" => $passHasheada
        ];

        // Agregar el nuevo usuario al array
        $usuarios[] = $nuevoUsuario;

        // Guardar los usuarios en el archivo JSON
        file_put_contents("usuarios.json", json_encode($usuarios, JSON_PRETTY_PRINT));

        // Redirigir al usuario a la página de inicio de sesión
        header("Location: login.php");
        exit();
    }
    ?>
</body>
</html>