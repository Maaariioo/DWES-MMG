<?php
session_start();
include "partidos.inc";

if (!isset($_REQUEST["partido"]) || !is_numeric($_REQUEST["partido"]) || $_REQUEST["partido"] < 0 || $_REQUEST["partido"] >= count($partidos)) {
    echo "Error, partido inválido";
    echo '<p><a href="Marcadores.php">Volver al listado (actualizar antes)</a></p>';
    exit();
}

$partido = $_REQUEST["partido"];
$equipos = explode(":", $partidos[$partido]);

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $_SESSION['marcadores'][$partido]['local'] = $_POST['local'];
    $_SESSION['marcadores'][$partido]['visitante'] = $_POST['visitante'];
    $_SESSION['marcadores'][$partido]['minuto'] = $_POST['minuto'];
}

$plocal = $_SESSION['marcadores'][$partido]['local'];
$pvisitante = $_SESSION['marcadores'][$partido]['visitante'];
$minuto = $_SESSION['marcadores'][$partido]['minuto'];
?>

<html>
<head>
<meta charset="utf-8">
<title>Editor de marcador</title>
</head>
<body>
<h2>Editar marcador del partido: <?= $equipos[0] ?> vs <?= $equipos[1] ?></h2>

<form method="POST">
    <input type="hidden" name="partido" value="<?= $partido ?>">

    <?= $equipos[0] ?>: <input type="number" name="local" value="<?= $plocal ?>" />
    <?= $equipos[1] ?>: <input type="number" name="visitante" value="<?= $pvisitante ?>" />
    Minuto: <input type="number" name="minuto" value="<?= $minuto ?>" />

    <input type="submit" value="Actualizar">
</form>

<p><a href="Marcadores.php">Volver al listado (actualizar antes)</a></p>
</body>
</html>
