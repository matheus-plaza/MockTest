package io.github.matheusplaza.mock;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {
    // Testes de integração com banco de dados em memória H2

    static Connection conexao;

    @BeforeAll
    static void setUpDatabase() throws Exception {
        conexao = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

        conexao.createStatement().execute("CREATE TABLE users (" +
                "id INT, name VARCHAR)");
    }

    @BeforeEach
    void insertUserTest() throws Exception {
        conexao.createStatement().execute("INSERT INTO users (id, name) VALUES (1, 'Matheus')");
    }

    @Test
//    @Disabled
    void testUserExists() throws Exception {
        var resultSet = conexao.createStatement().executeQuery("SELECT * FROM users WHERE id = 1");
        Assertions.assertTrue(resultSet.next());
    }

    @AfterAll
    static void closeDatabase() throws Exception {
        conexao.close();
    }
}