import Account.Account;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AccountTest {
    @Test public void shouldReturnNoTransactionWhenCreated (){
        Account account = new Account();

        String stmt = account.getReport();

        assertThat(stmt, is("DATE | AMOUNT | BALANCE\n"));
    }

    @Test public void shouldPrintNoTransactionsWhenCreated(){
        PrintStream printStream = mock(PrintStream.class);
        Account account = new Account();
        account.print(printStream);
        verify(printStream, times(1)).print("DATE | AMOUNT | BALANCE\n");
    }

    @Test public void shouldReportSingleTransactionWithCorrectFormattingAndBalance() {
        Account account = new Account();
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2012, Calendar.JANUARY, 10);
        account.deposit(calendar.getTime(), 100);
        String report = account.getReport();
        assertThat(report, equalTo("DATE | AMOUNT | BALANCE\n10/01/2012 | 100,00 | 100,00\n"));
    }

    @Test public void shouldReportWithdrawalWhenWithdrawing() {
        Account account = new Account();
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2012, Calendar.JANUARY, 10);
        account.withdraw(calendar.getTime(), 500);
        String report = account.getReport();
        assertThat(report, equalTo("DATE | AMOUNT | BALANCE\n10/01/2012 | -500,00 | 500,00\n"));
    }

    @Test public void shouldReportHistoryOfTransactions() {
        Account account = new Account();
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2014, Calendar.APRIL, 1);
        account.deposit(calendar.getTime(), 1000);
        calendar.clear();
        calendar.set(2014, Calendar.APRIL, 2);
        account.withdraw(calendar.getTime(), 100);
        calendar.clear();
        calendar.set(2014, Calendar.APRIL, 10);
        account.deposit(calendar.getTime(), 500);
        String report = account.getReport();
        assertThat(report, equalTo("DATE | AMOUNT | BALANCE\n10/04/2014 | 500,00 | 1 400,00\n02/04/2014 | -100,00 | 900,00\n01/04/2014 | 1 000,00 | 1 000,00\n"));

    }
}
