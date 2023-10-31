class BankAccount{
    accountNumber:string;
    accountHolder:string;
    balance:number;
    transactions:Transaction[];

    constructor(accountNumber:string,accountHolder:string,balance:number){
        this.accountNumber = accountNumber;
        this.accountHolder =accountHolder;
        this.balance = balance;
        this.transactions=[];
    }

    deposit(amount: number){
        this.balance+=amount;
        this.transactions.push({type:"deposit", amount:amount, timeStamp:new Date()});
        console.log(`Amount Deposited!`);
    }

    withdraw(amount: number){
        if(this.balance<amount){
            console.log("Insufficient Funds!")
        }
        else{
            this.balance-=amount;
            this.transactions.push({type:"withdraw",amount:amount,timeStamp:new Date()});
            console.log(`Amount Withdrawn`);
        }
    }

    getBalance(){
        console.log(this.balance);
    }
}


interface Transaction{
    type : "deposit" | "withdraw";
    amount:number;
    timeStamp:Date;
 }

let account = new BankAccount("123","Kishan",20000);
account.withdraw(25000);
account.deposit(10000);
account.withdraw(5000);
account.getBalance();