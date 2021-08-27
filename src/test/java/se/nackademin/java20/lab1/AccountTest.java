package se.nackademin.java20.lab1;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.nackademin.java20.lab1.domain.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-27
 * Project: lab2
 */

public class AccountTest {

    @Autowired
    Account account;


    @BeforeEach
    public void init(){
        account = new Account("dan", 0);
    }

    @Test
    void oppenAccountTest(){

        assertEquals(0, account.getBalance());
    }
    
    @Test
    void depositTest(){
        account.deposit(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawTest(){
        account.deposit(100);
        account.withdraw(50);
        assertEquals(50, account.getBalance());
    }

    @Test
    void withdrawExe(){
        assertThrows(IllegalStateException.class, ()-> account.withdraw(200));
    }

}
