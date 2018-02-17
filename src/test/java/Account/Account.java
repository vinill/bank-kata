package Account;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private Date lastDate;
    private float balance;
    private float lastTransaction;
    String header = "DATE | AMOUNT | BALANCE\n";

    public void print(PrintStream printStream) {

        printStream.print(header);

    }

    public void deposit(Date time, int i) {
        lastDate=time;
        lastTransaction = i;
        balance += i;

    }

    public String getReport() {
        if (lastDate == null)
            return header;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        return header + format.format(lastDate) + " | " +  decimalFormat.format(lastTransaction) + " | " + decimalFormat.format(balance) + "\n";
    }

    public void withdraw(Date time, int i) {
        lastDate = time;
        lastTransaction = -i;
        balance -= i;
    }
}
