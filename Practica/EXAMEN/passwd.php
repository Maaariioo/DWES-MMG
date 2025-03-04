<!-- Mostrar solo si no se ha autenticado ya -->
<?php
session_start();
if (!isset($_SESSION['usuario'])) {  // Si el usuario no está autenticado
?>
<form method="post">
<p>
<label for="usuario">Usuario:</label>
<input type="text" name="usuario" />
</p>
<p>
<label for="contrasena">Contraseña:</label>
<input type="password" name="contrasena" />
</p>
<p><input type="submit" value="Iniciar sesión" /></p>
</form>
<?php
} else {
    echo "Bienvenido " . $_SESSION['usuario'];
}
?>
<?php

$passwd = array(
"user1" => '$2y$10$3SgeyVJD/mw0rvRtjkwWk.8XM1.GpDr8NWc95bN.tpSK2sEriXtX2',
"user2" => '$2y$10$OymrA3YaPJ4Pfnh.H3GOneuD2y5OklZVW28OxrniOdkDyLWPN/80u',
"user3" => '$2y$10$qmNdtRRxXXfVRQy8Sjb7bey1RZqj.I2sz3NvUO2kuz2hjZHud2rrC',
"user4" => '$2y$10$s4PyteQlBtBLtdL0pO9jQ.3TEB0z2kAMGfQCmpNig91Q8o8hwEZY6',
"user5" => '$2y$10$kNiEqiag6PPROoFQM9E6BuEEVYPm6Lev9OS7y9N20FdRnTeCqBC0y',
"user6" => '$2y$10$CrbCkq6F.RCI4nyydjXwKu5hSRf4ZSrNi6J5P3jJJ8Fl4JlTz57Y2',
"user7" => '$2y$10$rXNAShF5ADOowmIV.82KnuMHntgpbvG5oQtFs1/KQrKCkipDTK2BS',
"user8" => '$2y$10$FFkEH4zNMAC5R8UPFahqMeYdVqQtSfMMW0oLD6e4zOTWyTtZWmSJG',
"user9" => '$2y$10$ClccGXvtRiKGkwgh4fhNKOLqnYDs/ta2bqbeiA4o7RVrZ0Koiz1kG',
"user10" => '$2y$10$dX8LQLCIcJc5IwHqdP1aVOiINd0SF1IfPu8xzf4tnCyxuIonXRbf.'
);

session_start(); // Inicia la sesión

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $usuario = $_POST['usuario'];  // Recupera el nombre de usuario
    $contrasena = $_POST['contrasena'];  // Recupera la contraseña

    // Verifica si el usuario existe y si la contraseña es correcta
    if (isset($passwd[$usuario]) && password_verify($contrasena, $passwd[$usuario])) {
        // Si la autenticación es exitosa, se inicia sesión
        $_SESSION['usuario'] = $usuario;  // Guarda el nombre de usuario en la sesión
        echo "Bienvenido $usuario";  // Muestra el mensaje de bienvenida
    } else {
        // Si las credenciales son incorrectas
        echo "Usuario o contraseña incorrectos.";
    }
}

?>


