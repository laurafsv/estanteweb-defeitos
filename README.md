# EstanteWeb

Projeto de Programação para Web 3.

Aqui dá para ver, cadastrar, editar e excluir livros. Também tem busca por título e autor, sugestões de leitura e login para mexer no acervo.

## Como rodar

Primeiro, inicie o MySQL pelo Docker:

```sh
docker run --name estanteweb-mysql -e MYSQL_ROOT_PASSWORD=estante -e MYSQL_DATABASE=estanteweb -p 3306:3306 -d mysql:8
```

Se o contêiner já existir, use `docker start estanteweb-mysql`.

Depois, rode:

```sh
mvn spring-boot:run
```

Abra `http://localhost:8080/livros/html`.

## Login de teste

- E-mail: `marta@estanteweb.com`
- Senha: `estante123`

## Páginas

- `/livros/html`: lista e busca de livros.
- `/sugestoes`: lista e busca de sugestões.
- `/login`: entrar.
- `POST /logout`: sair pelo botão.
