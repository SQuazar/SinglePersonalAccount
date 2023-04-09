package net.quazar.backend.service;

import net.quazar.backend.entity.Account;
import net.quazar.backend.entity.Role;

import java.util.List;

public interface AccountService {
    Account findByUsername(String username);
    Account createAccount(String username, String password, String fullName);
    List<Role> addRole(Account account, Role role);
    List<Role> removeRole(Account account, Role role);
    boolean changePassword(Account account, String oldPassword, String newPassword);
    List<Account> findAll();
}
