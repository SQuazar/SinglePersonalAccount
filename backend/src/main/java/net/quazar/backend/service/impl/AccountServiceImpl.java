package net.quazar.backend.service.impl;

import lombok.AllArgsConstructor;
import net.quazar.backend.entity.Account;
import net.quazar.backend.entity.Role;
import net.quazar.backend.exception.AccountNotFoundException;
import net.quazar.backend.repository.AccountRepository;
import net.quazar.backend.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() ->
                new AccountNotFoundException(String.format("Cannot find account with username %s", username)));
    }

    @Override
    public Account createAccount(String username, String password, String fullName) {
        Account account = new Account();
        account.setUsername(username);
        account.setPasswordHash(passwordEncoder.encode(password));
        account.setFullName(fullName);
        return repository.save(account);
    }

    @Override
    public List<Role> addRole(Account account, Role role) {
        account.getRoles().add(role);
        return repository.save(account).getRoles().stream().toList();
    }

    @Override
    public List<Role> removeRole(Account account, Role role) {
        account.getRoles().remove(role);
        return repository.save(account).getRoles().stream().toList();
    }

    @Override
    public boolean changePassword(Account account, String oldPassword, String newPassword) {
        String passwordHash;
        if (!(passwordHash = passwordEncoder.encode(newPassword)).equals(oldPassword))
            return false;
        account.setPasswordHash(passwordHash);
        repository.save(account);
        return true;
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }
}
