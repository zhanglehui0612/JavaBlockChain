package com.blockchain;

import lombok.Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Block {
    // 索引
    private int index;
    // 当前区块的hash
    public String hash;
    // 前一个区块的hash
    public String prevBlockHash;
    // 交易数据
    private String data;
    // 时间戳
    private long timestamp;

    private int nonce;

    private List<Transaction> transactions;

    public Block(int index, String prevBlockHash, List<Transaction> transactions) {
        this.index = index;
        this.prevBlockHash = prevBlockHash;
        this.transactions = transactions;
        this.timestamp = System.currentTimeMillis();
        this.hash = calculateHash();
        this.nonce = 0;
    }

    /**
     * 根据索引+前一个区块的hash+时间戳+挖矿难度系数或者随机值+交易信息计算一个哈希值
     * @return
     */
    public String calculateHash() {
        String data = index + prevBlockHash + timestamp + nonce + transactions.toString();
        return calculateSHA256(data);
    }

    /**
     * 挖矿
     * @param difficulty
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.startsWith(target)) {
            nonce++;
            hash = calculateHash();
        }

        System.out.println("添加区块是计算根据难度系数计算的哈希值: " + hash);
    }

    /**
     * 通过SHA256哈希函数创建哈希值
     * @param input
     * @return
     */
    private String calculateSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
