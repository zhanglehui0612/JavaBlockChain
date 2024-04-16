package com.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        BlockChain blockchain = new BlockChain(4);
        List<Transaction> transactions1 = new ArrayList<>();
        transactions1.add(new Transaction("0xC3b0F127","0xC3b0F128", 1.1d));
        transactions1.add(new Transaction("0xC3b0F129","0xC3b0F123", 0.1d));
        transactions1.add(new Transaction("0xC3b0F110","0xC3b0F122", 3.1d));
        transactions1.add(new Transaction("0xC3b0F111","0xC3b0F121", 1.2d));
        blockchain.addBlock(new Block(1, blockchain.getLatestBlock().hash, transactions1));

        List<Transaction> transactions2 = new ArrayList<>();
        transactions2.add(new Transaction("0xC4b0F127","0xC5b0F128", 2.1d));
        transactions2.add(new Transaction("0xC4b0F129","0xC5b0F123", 5.2d));
        transactions2.add(new Transaction("0xC4b0F110","0xC5b0F122", 0.1d));
        transactions2.add(new Transaction("0xC4b0F111","0xC5b0F121", 0.2d));
        blockchain.addBlock(new Block(2, blockchain.getLatestBlock().hash, transactions2));

        List<Transaction> transactions3 = new ArrayList<>();
        transactions3.add(new Transaction("0xC4b0F127","0xC5b0F128", 2.1d));
        transactions3.add(new Transaction("0xC4b0F129","0xC5b0F123", 5.2d));
        transactions3.add(new Transaction("0xC4b0F110","0xC5b0F122", 0.1d));
        transactions3.add(new Transaction("0xC4b0F111","0xC5b0F121", 0.2d));
        blockchain.addBlock(new Block(3, blockchain.getLatestBlock().hash, transactions3));


        List<Transaction> transactions4 = new ArrayList<>();
        transactions4.add(new Transaction("0xC8b0F127","0xC1b0F128", 0.3d));
        transactions4.add(new Transaction("0xC8b0F129","0xC1b0F123", 3.2d));
        transactions4.add(new Transaction("0xC8b0F110","0xC1b0F122", 6.1d));
        transactions4.add(new Transaction("0xC8b0F111","0xC1b0F121", 2.0d));
        blockchain.addBlock(new Block(4, blockchain.getLatestBlock().hash, transactions4));

        // 没有篡改区块链中数据，验证区跨链有效
        boolean isValidChain = blockchain.isChainValid();
        System.out.println("未修改区块链中交易数据，区块链是否是有效: " + isValidChain);

        // 篡改区块链中数据，验证区跨链无效
        blockchain.getLatestBlock().getTransactions().get(0).setAmount(100.0d);
        isValidChain = blockchain.isChainValid();
        System.out.println("修改区块链中交易数据，区块链是否是有效: " + isValidChain);
    }
}
