package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SugestaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Sugestao> listarTodas() {
        String sql = "SELECT * FROM sugestao";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);

        List<Sugestao> sugestoes = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            sugestoes.add(Sugestao.deMap(linha));
        }
        return sugestoes;
    }

    public List<Sugestao> buscar(String nomeCliente, String livroSugerido) {
        String sql = "SELECT * FROM sugestao";
        List<String> condicoes = new ArrayList<>();
        List<Object> parametros = new ArrayList<>();

        if (nomeCliente != null && !nomeCliente.isBlank()) {
            condicoes.add("nome_cliente LIKE ? ESCAPE '!'");
            parametros.add("%" + escaparCuringas(nomeCliente.trim()) + "%");
        }
        if (livroSugerido != null && !livroSugerido.isBlank()) {
            condicoes.add("livro_sugerido LIKE ? ESCAPE '!'");
            parametros.add("%" + escaparCuringas(livroSugerido.trim()) + "%");
        }
        if (!condicoes.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", condicoes);
        }

        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, parametros.toArray());
        List<Sugestao> sugestoes = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            sugestoes.add(Sugestao.deMap(linha));
        }
        return sugestoes;
    }

    private String escaparCuringas(String texto) {
        return texto.replace("!", "!!").replace("%", "!%").replace("_", "!_");
    }

    public void salvar(Sugestao sugestao) {
        String sql = "INSERT INTO sugestao (nome_cliente, livro_sugerido) VALUES (?, ?)";
        jdbcTemplate.update(sql, sugestao.getNomeCliente(), sugestao.getLivroSugerido());
    }
}
