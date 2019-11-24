package com.company.threadsadvice.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

  public static void main(String[] args) throws InterruptedException {
    Runner runner = new Runner();

    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        runner.firstThread();
      }
    });
    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        runner.secondThread();
      }
    });

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    runner.finished();

  }
}

class Runner {

  private Account account1 = new Account();
  private Account account2 = new Account();
  private Lock lock1 = new ReentrantLock();
  private Lock lock2 = new ReentrantLock();

  private void takeLocks(Lock lock1, Lock lock2)  {
    boolean firstLockTaken = false;
    boolean secondLockTaken = false;
    while(true) {
      //try get lock
      try {
        firstLockTaken = lock1.tryLock();
        secondLockTaken = lock2.tryLock();
      } finally {
        if (firstLockTaken && secondLockTaken) {
          return;
        }
        if (firstLockTaken) {
          lock1.unlock();
        }
        if (secondLockTaken) {
          lock2.unlock();
        }
      }
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void firstThread()  {
    Random random = new Random();

    for (int i = 0; i < 10000; i++) {
      takeLocks(lock1, lock2);
      try {
      Account.transfer(account1, account2, random.nextInt(100));
    } finally {
      lock1.unlock();
      lock2.unlock();
    }
    }
  }

  public void secondThread() {
    Random random = new Random();
    for (int i = 0; i < 10000; i++) {
      takeLocks(lock2, lock1);
      try {
        Account.transfer(account2, account1, random.nextInt(100));
      } finally {
        lock1.unlock();
        lock2.unlock();
      }

    }
  }

  public void finished() {
    System.out.println(account1.getBalance());
    System.out.println(account2.getBalance());
    System.out.println("Total account : " + (account2.getBalance() + account1.getBalance()));
  }
}

class Account {

  private int balance = 10_000;

  public void deposit(int amount) {
    balance += amount;
  }

  public void withdraw(int amount) {
    balance -= amount;
  }

  public int getBalance() {
    return balance;
  }

  public static void transfer(Account acc1, Account acc2, int money) {
    acc1.withdraw(money);
    acc2.deposit(money);
  }
}
