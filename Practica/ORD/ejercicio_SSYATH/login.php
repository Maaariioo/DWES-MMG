<!DOCTYPE html>
<?php session_start()?>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin: 50px; }
        .container { width: 300px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; background-color: #f9f9f9; }
        input, button { width: 100%; margin: 5px 0; padding: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Iniciar Sesión</h2>
        <form action="login.php" method="POST">
            <input type="email" name="email" placeholder="Correo electrónico" required>
            <input type="password" name="password" placeholder="Contraseña" required>
            <button type="submit">Ingresar</button>
        </form>
        <a href="registro.php">Registrarse</a>
        <a href="index.php">Volver al inicio</a>
    </div>

    <?php

        if($_SERVER["REQUEST_METHOD"] === "POST"){

            $email = $_POST['email'];
            $pass = $_POST['password'];

            $usuarios = json_decode(file_get_contents("usuarios.json"),true);

            $loginExitoso = false;

            foreach ($usuarios as $usuario) {

                if ($usuario["email"] === $email && password_verify($pass, $usuario["password"])) {
                    $loginExitoso = true; 
                    break;
                }

        }

        if ($loginExitoso){

            $passHasehada = password_hash($pass,PASSWORD_DEFAULT);

            $usuarioIniciado = [

                "email" => $email,
                "pass" => $passHasehada

            ];

            $_SESSION['usuario'] = $usuarioIniciado;
            header("Location: protegida.php");
            exit;

        }else{

            echo "<p style = color:red;>Email o Contraseña incorrectas</p>";
        }
    }

    ?>

</body>
</html>
