package org.tim.domain;

import java.util.Date;

/**
 * @author tim
 */
public class Account extends Entity {
    private String bankname;
    private long amount;
    private int rate;
    private Date expire;

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %d%%, %4$td.%4$tm.%4$tY", bankname, amount, rate, expire);
    }
}
