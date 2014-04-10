package org.tim.domain;

import java.util.Date;

/**
 * @author tim
 */
public class Account extends Entity {
    private String bank;
    private long amount;
    private int percent;
    private Date expire;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %d%%, %4$td.%4$tm.%4$tY", bank, amount, percent, expire);
    }
}
