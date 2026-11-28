<#include "cabecalho.ftlh">

<h1>Acervo</h1>
<form method="get" action="/livros/html">
    <label for="q">Título:</label>
    <input type="search" id="q" name="q" value="${q!}" placeholder="Buscar por título">
    <label for="autor">Autor:</label>
    <input type="search" id="autor" name="autor" value="${autor!}" placeholder="Buscar por autor">
    <#if (ordem!'')?has_content>
        <input type="hidden" name="ordem" value="${ordem}">
    </#if>
    <button type="submit">Buscar</button>
    <a href="/livros/html">Limpar</a>
</form>

<p>
    Ordenar por:
    <a href="/livros/html?ordem=titulo&amp;q=${(q!'')?url('UTF-8')}&amp;autor=${(autor!'')?url('UTF-8')}">título</a>
    <a href="/livros/html?ordem=autor&amp;q=${(q!'')?url('UTF-8')}&amp;autor=${(autor!'')?url('UTF-8')}">autor</a>
    <a href="/livros/html?ordem=preco&amp;q=${(q!'')?url('UTF-8')}&amp;autor=${(autor!'')?url('UTF-8')}">preço</a>
</p>

<#if usuarioLogado??>
    <p><a href="/livros/novo">Cadastrar livro</a></p>
</#if>

<p>${livros?size} livro(s) encontrado(s).</p>
<#if livros?size == 0>
    <#if (q!'')?has_content || (autor!'')?has_content>
        <p>Nenhum livro encontrado para
        <#if (q!'')?has_content>o título "${q}"</#if>
        <#if (q!'')?has_content && (autor!'')?has_content> e </#if>
        <#if (autor!'')?has_content>o autor "${autor}"</#if>.</p>
    <#else>
        <p>Nenhum livro cadastrado no acervo.</p>
    </#if>
<#else>
    <table>
        <tr><th>Título</th><th>Autor</th><th>Ano</th><th>Preço</th></tr>
        <#list livros as livro>
            <tr>
                <td><a href="/livros/${livro.id?c}">${livro.titulo}</a></td>
                <td>${livro.autor}</td>
                <td>${livro.ano?c}</td>
                <td>R$ ${livro.preco?string("0.00")}</td>
            </tr>
        </#list>
    </table>
</#if>
</body>
</html>
