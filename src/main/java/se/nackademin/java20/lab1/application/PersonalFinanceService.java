package se.nackademin.java20.lab1.application;

import se.nackademin.java20.lab1.Risk.RestRiskAssesment;
import se.nackademin.java20.lab1.domain.Account;
import se.nackademin.java20.lab1.domain.AccountRepository;
import se.nackademin.java20.lab1.domain.RiskAssesment;

import javax.transaction.Transactional;

public class PersonalFinanceService {
    private final AccountRepository accountRepository;
    private final RiskAssesment riskAssesment;

    public PersonalFinanceService(AccountRepository accountRepository, RiskAssesment riskAssesment) {
        this.accountRepository = accountRepository;
        this.riskAssesment = riskAssesment;
    }

    @Transactional  //here is where we put the apps together. We anropar riskAssessment.
    public Account openAccount(String holder) {
        if (riskAssesment.isItApproved(holder)) {
            return accountRepository.save(new Account(holder, 0));
        } else {
            throw new RuntimeException("Risk assessment could not be done due to risks");
        }
    }

    @Transactional
    public Account withdraw(long id, String holder, long amount) {
        Account account = accountRepository.findByUserIdAndAccountId(holder, id).orElseThrow(() -> new RuntimeException("Could not find account"));
        account.withdraw(amount);
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account deposit(long id, String holder, long amount) {
        Account account = accountRepository.findByUserIdAndAccountId(holder, id).orElseThrow(() -> new RuntimeException("Could not find account"));
        account.deposit(amount);
        accountRepository.save(account);
        return account;
    }

    public Account findAccount(String userId, Long accountId) {
        return accountRepository.findByUserIdAndAccountId(userId, accountId).orElseThrow(() -> new RuntimeException("Could not find account"));
    }
}
