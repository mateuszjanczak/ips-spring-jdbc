package com.mateuszjanczak.ips.domain.repository;

import com.mateuszjanczak.ips.domain.entity.Customer;
import com.mateuszjanczak.ips.domain.mapper.CustomerMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerMapper customerMapper;

    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate, CustomerMapper customerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM ips.klient ORDER BY id_uzytkownika";
        return jdbcTemplate.query(sql, customerMapper);
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM ips.klient WHERE id_uzytkownika = ?";
        return jdbcTemplate.queryForObject(sql, customerMapper, id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Customer save(Customer customer) {

        int userId = jdbcTemplate.queryForObject("SELECT id_uzytkownika FROM ips.uzytkownik ORDER BY id_uzytkownika DESC LIMIT 1", Integer.class) + 1;
        String userSql = "INSERT INTO ips.uzytkownik(id_uzytkownika, nazwa_uzytkownika, haslo, email) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(userSql, userId, customer.getImie() + userId, customer.getNazwisko() + userId, customer.getNazwisko() + userId + "@gmail.com");

        int customerId = jdbcTemplate.queryForObject("SELECT id_uzytkownika FROM ips.klient ORDER BY id_uzytkownika DESC LIMIT 1", Integer.class) + 1;
        String sql = "INSERT INTO ips.klient(id_klienta, id_adresu, id_uzytkownika, imie, nazwisko, pesel, telefon) VALUES(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, userId, 1, customerId, customer.getImie(), customer.getNazwisko(), customer.getPesel(), customer.getTelefon());

        return findById(customerId);
    }

    @Override
    public List<Customer> findAll(int limit, int page) {
        String sql = "SELECT * FROM ips.klient ORDER BY id_uzytkownika LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, customerMapper, limit, limit * page);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM ips.klient";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public boolean remove(int id) {
        String sql = "DELETE FROM ips.klient WHERE id_uzytkownika = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public Customer update(int id, Customer customer) {
        String sql = "UPDATE ips.klient SET imie = ?, nazwisko = ?, pesel  = ?, telefon = ? WHERE id_uzytkownika = ?";
        jdbcTemplate.update(sql, customer.getImie(), customer.getNazwisko(), customer.getPesel(), customer.getTelefon(), id);
        return findById(id);
    }
}
