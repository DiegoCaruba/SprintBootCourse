package com.ibm.diegocaruba;

import com.ibm.diegocaruba.domain.entity.Cliente;
import com.ibm.diegocaruba.domain.repositorio.ClientesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesDao clientes) {
        return args -> {

            System.out.println("SALVANDO");
            clientes.salvar(new Cliente("Diego"));
            clientes.salvar(new Cliente("Freya"));
            clientes.salvar(new Cliente("Gaia"));
            clientes.salvar(new Cliente("Amy"));

            System.out.println("===> LISTANDO");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("ATUALIZANDO");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.atualizar(c);
            });

            System.out.println("===> LISTANDO");
            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("BUSCANDO");
            clientes.buscarNome("Fre").forEach(System.out::println);

            System.out.println("DELETANDO");
            clientes.deletar(1);

            System.out.println("===> LISTANDO");
            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("DELETANDO");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            System.out.println("===> LISTANDO");
            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()) {
                System.out.println("NENHUM CLIENTE ENCONTRADO");
            } else {
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
