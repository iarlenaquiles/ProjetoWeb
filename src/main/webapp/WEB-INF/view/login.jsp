<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="url_base" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página Login</title>
<link rel="stylesheet" href="${url_base}css/bootstrap.min.css" />
</head>
<body>
<div class="container">
<h1>Login</h1>
<form action="${url_base}/mvc?controller=LoginController&action=login" method="POST">
	<input name="usuario" type="text">
	<input name="senha" type="password">
	<button type="submit">Enviar</button>
</form>
</div>
<script src="${url_base}js/jquery-3.2.1.min.js"></script>
<script src="${url_base}js/bootstrap.min.js"></script>
</body>
</html>