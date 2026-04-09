public class Test {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Person person = new Person("Alice", 30);
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Account Number: " + person.getAccount().getAccountNumber());
        System.out.println("Balance: " + person.getAccount().getBalance());



        System.out.println("\n\n\n\n-----------------------------");
        Account acc = new BankAcc("987654321", 2000.0, "Bank of Java");
    }
}



class Person {
    private String name;
    private int age;
    private Account account;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        account = new Account("123456789", 1000.0);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Account getAccount() {
        return account;
    }

}

class Account {
    private String accountNumber;
    private double balance;
    public int test = 0;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}

class BankAcc extends Account {
    private String bankName;
    public String test = "1abc";

    public BankAcc(String accountNumber, double balance, String bankName) {
        super(accountNumber, balance);
        this.bankName = bankName;

        System.out.println(test);
        System.out.println(super.test);
    }

    public String getBankName() {
        return bankName;
    }

    
}