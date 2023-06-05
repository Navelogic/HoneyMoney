package br.com.honeymoney.api.repository;

import br.com.honeymoney.api.domain.User;

import java.util.Collection;

/**
 * Repositório para operações de usuários.
 *
 * @param <T> tipo de entidade de usuário.
 */
public interface UserRepository<T extends User> {
    /**
     * Cria um novo usuário.
     *
     * @param data dados do usuário a ser criado
     * @return o usuário criado
     */
    T save(T data);

    /**
     * Retorna uma coleção de usuários paginada.
     *
     * @param page     número da página (opcional)
     * @param pageSize tamanho da página (opcional)
     * @return a coleção de usuários paginada
     */
    Collection<T> findAll(int page, int pageSize);

    /**
     * Retorna um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser obtido
     * @return o usuário correspondente ao ID, ou null se não encontrado
     */
    T findById(Long id);

    /**
     * Atualiza um usuário existente.
     *
     * @param data dados do usuário a serem atualizados
     * @return o usuário atualizado
     */
    T updateUser(T data);

    /**
     * Exclui um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser excluído
     * @return true se o usuário foi excluído com sucesso, false caso contrário
     */
    boolean deleteById(Long id);

    /* Other methods */


}
