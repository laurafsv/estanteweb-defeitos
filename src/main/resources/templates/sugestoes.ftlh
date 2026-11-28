<#include "cabecalho.ftlh">

<h1>Sugestões de leitura</h1>
<form method="get" action="/sugestoes">
    <label for="nomeCliente">Nome do cliente:</label>
    <input type="search" id="nomeCliente" name="nomeCliente" value="${nomeCliente!}" placeholder="Buscar por cliente">
    <label for="livroSugerido">Livro sugerido:</label>
    <input type="search" id="livroSugerido" name="livroSugerido" value="${livroSugerido!}" placeholder="Buscar por livro">
    <button type="submit">Buscar</button>
    <a href="/sugestoes">Limpar</a>
</form>

<p>${sugestoes?size} sugestão(ões) encontrada(s).</p>
<#if sugestoes?size == 0>
    <#if (nomeCliente!'')?has_content || (livroSugerido!'')?has_content>
        <p>Nenhuma sugestão encontrada para
        <#if (nomeCliente!'')?has_content>o cliente "${nomeCliente}"</#if>
        <#if (nomeCliente!'')?has_content && (livroSugerido!'')?has_content> e </#if>
        <#if (livroSugerido!'')?has_content>o livro "${livroSugerido}"</#if>.</p>
    <#else>
        <p>Nenhuma sugestão recebida ainda.</p>
    </#if>
<#else>
    <ul>
        <#list sugestoes as sugestao>
            <li>${sugestao.nomeCliente} sugeriu: ${sugestao.livroSugerido}</li>
        </#list>
    </ul>
</#if>

<p><a href="/sugestoes/nova">Sugerir um livro</a></p>
</body>
</html>
