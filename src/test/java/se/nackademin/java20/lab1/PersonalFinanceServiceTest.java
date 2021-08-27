package se.nackademin.java20.lab1;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.nackademin.java20.lab1.application.PersonalFinanceService;
import se.nackademin.java20.lab1.domain.Account;
import se.nackademin.java20.lab1.domain.AccountRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-27
 * Project: lab2
 */

public class PersonalFinanceServiceTest {

    @Test
    void canDeposit(){
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.findByUserIdAndAccountId("Dan", 1)).
                thenReturn(Optional.of(new Account("Dan", 0)));
        PersonalFinanceService personalFinanceService = new PersonalFinanceService(accountRepository);

        Account account = personalFinanceService.deposit(1, "Dan", 100);
        assertEquals(account.getBalance(), 100);
        verify(accountRepository,times(1)).save(any());
    }



    public class Katter {

        private String name;
        private String age;

        public Katter(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

    }

    @Test
    void iskatt() {
        Katter katt = mock(Katter.class);
        when(katt.getName()).thenReturn("blanca");

        assertEquals("blanca", katt.getName());
        verify(katt, times(1)).getName();
    }
}


