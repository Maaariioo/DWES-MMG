<?php session_start();

if (!isset($_SESSION["usuario"])){

    header("Location: registro.php");

}

?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Protegida</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 50px;
            background-color: #f4f4f4;
        }
        .container {
            width: 300px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        p {
            color: #666;
        }
        button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenido a la Página Protegida</h1>
        <p>Has iniciado sesión correctamente.</p>
        <form method="POST">
            <button value="logout" type="submit">Cerrar Session</button>
        </form>
    </div>

    <?php
    
    if ($_SERVER['REQUEST_METHOD'] == "POST"){

        session_destroy();
        header("Location: login.php");

    }

    ?>
</body>
</html>