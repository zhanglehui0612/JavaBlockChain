package com.blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    // 发送者
    private String sender;
    // 接收者
    private String recipient;
    // 交易金额
    private double amount;


    @Override
    public String toString() {
        return sender + recipient + amount;
    }
}
