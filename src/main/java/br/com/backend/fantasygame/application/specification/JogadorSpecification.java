package br.com.backend.fantasygame.application.specification;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.infrastracture.schema.JogadorSchema;
import org.springframework.data.jpa.domain.Specification;

public class JogadorSpecification {

    public static Specification<JogadorSchema> nome(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<JogadorSchema> pais(Long paisId) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("pais"), paisId);
    }

    public static Specification<JogadorSchema> posicao(Long posicaiId) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("posicao"), posicaiId);
    }
}
