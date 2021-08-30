package se.nackademin.java20.lab1;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import se.nackademin.java20.lab1.Risk.RestRiskAssesment;
import se.nackademin.java20.lab1.application.PersonalFinanceService;
import se.nackademin.java20.lab1.domain.Account;
import se.nackademin.java20.lab1.domain.AccountRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-27
 * Project: lab2
 */

public class PersonalFinanceServiceTest {


    @Test
    void canOpenBankAccount() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        RestRiskAssesment restRiskAssesment = mock(RestRiskAssesment.class);
        //we want to test the application, so we mockar repository and adapter.
        PersonalFinanceService personalFinanceService = new PersonalFinanceService(accountRepository, restRiskAssesment);
        when(accountRepository.save(any(Account.class))).thenReturn(new Account("Blancusha", 0));
        when(restRiskAssesment.isItApproved("Blancusha")).thenReturn(true);

        final Account account = personalFinanceService.openAccount("Blancusha");

        //we test the real methods results with the data that we mocked.

        assertEquals(account.getHolder(), "Blancusha");
        assertEquals(account.getBalance(), 0);
        verify(restRiskAssesment).isItApproved(anyString());
        verify(accountRepository).save(any());
    }

    @Test
    void donotOpenBankAccountRiskFail(){
        AccountRepository accountRepository = mock(AccountRepository.class);
        RestRiskAssesment restRiskAssesment = mock(RestRiskAssesment.class);
        //we want to test the application, so we mockar repository and adapter.
        PersonalFinanceService personalFinanceService = new PersonalFinanceService(accountRepository, restRiskAssesment);


        //Here we dont test save() because the risk assessment has fail. We never get to save point.
        when(restRiskAssesment.isItApproved("Blanca")).thenReturn(false);
        assertThrows(RuntimeException.class, ()-> personalFinanceService.openAccount("Blanca"));
        verifyNoInteractions(accountRepository); //we never got to account repository.save

    }


        @Test
        void OpenBankAccounTest() {
            AccountRepository accountRepository = mock(AccountRepository.class);
            RestRiskAssesment restRiskAssesment = mock(RestRiskAssesment.class);
            PersonalFinanceService personalFinanceService = new PersonalFinanceService(accountRepository, restRiskAssesment);
            when(accountRepository.findByUserIdAndAccountId("Dan", 1)).thenReturn(Optional.of(new Account("Dan", 100)));


            Account account = personalFinanceService.deposit(1, "Dan", 100);
            assertEquals(account.getBalance(), 200);
            verify(accountRepository, times(1)).save(any());
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


