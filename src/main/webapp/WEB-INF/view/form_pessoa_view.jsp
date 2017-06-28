<form method="post"
	action="${url_base}mvc?controller=PessoaController&action=${acao}">
	<input name="id" value="${pessoa.id}" type="hidden" /> <label
		for="nome">Nome</label> <input name="nome" value="${pessoa.nome}"
		type="text" class="form-control" disabled /> <label for="email">Email</label>
	<input name="email" value="${pessoa.email}" type="text"
		class="form-control" disabled /> <label for="endereco">Endereço</label>
	<input name="endereco" value="${pessoa.endereco}" type="text"
		class="form-control" disabled /> <a
		href="${url_base}mvc?controller=PessoaController&action=lista"
		class="btn btn-primary">Voltar</a>
</form>
