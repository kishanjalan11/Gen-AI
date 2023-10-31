var BankAccount = /** @class */ (function () {
    function BankAccount(accountNumber, accountHolder, balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactions = [];
    }
    BankAccount.prototype.deposit = function (amount) {
        this.balance += amount;
        this.transactions.push({ type: "deposit", amount: amount, timeStamp: new Date() });
        console.log("Amount Deposited!");
    };
    BankAccount.prototype.withdraw = function (amount) {
        if (this.balance < amount) {
            console.log("Insufficient Funds!");
        }
        else {
            this.balance -= amount;
            this.transactions.push({ type: "withdraw", amount: amount, timeStamp: new Date() });
            console.log("Amount Withdrawn");
        }
    };
    BankAccount.prototype.getBalance = function () {
        console.log(this.balance);
    };
    return BankAccount;
}());
var account = new BankAccount("123", "Kishan", 20000);
account.withdraw(25000);
account.deposit(10000);
account.withdraw(5000);
account.getBalance();
