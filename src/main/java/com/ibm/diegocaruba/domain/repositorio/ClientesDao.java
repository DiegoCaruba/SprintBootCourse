package com.ibm.diegocaruba.domain.repositorio;

import ch.qos.logback.core.net.server.Client;
import com.ibm.diegocaruba.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesDao {

    private static String INSERT = "INSERT INTO cliente (nome) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM cliente";
    private static String UPDATE = "UPDATE cliente SET nome = ? WHERE id = ?";
    private static String DELETE = "DELETE FROM cliente WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente ) {
        entityManager.persist(cliente);
//        jdbcTemplate.update(INSERT, new Object[]{ cliente.getNome() } );
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        entityManager.merge(cliente);
//        jdbcTemplate.update(UPDATE, new Object[]{ cliente.getNome(), cliente.getId() });
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
//        deletar(cliente.getId());
        if(!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id) {
//        jdbcTemplate.update(DELETE, new Object[]{ id });
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarNome(String nome) {
//       return jdbcTemplate.query(
//               SELECT_ALL.concat("  WHERE nome LIKE ?"),
//               new Object[]{"%" + nome + "%"},
//               obterClienteMapper()
//       );
        String jpql = " SELECT c FROM Cliente c WHERE c.nome LIKE :nome ";
        TypedQuery<Cliente> query =  entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return  query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
//        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
        return  entityManager
                .createQuery("FROM Cliente", Cliente.class)
                .getResultList();
    }

//    private  RowMapper<Cliente> obterClienteMapper() {
//        return new RowMapper<Cliente>() {
//            @Override
//            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Integer id = rs.getInt("id");
//                String nome = rs.getString("nome");
//                return new Cliente(id, nome);
//            }
//        };
//    }
}


