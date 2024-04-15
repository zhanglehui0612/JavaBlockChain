package com.blockchain;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        BlockChain blockchain = new BlockChain(4);
        blockchain.addBlock(new Block(1, blockchain.getLatestBlock().hash, new ArrayList<>()));
        blockchain.addBlock(new Block(2, blockchain.getLatestBlock().hash, new ArrayList<>()));
        blockchain.addBlock(new Block(3, blockchain.getLatestBlock().hash, new ArrayList<>()));
        blockchain.addBlock(new Block(4, blockchain.getLatestBlock().hash, new ArrayList<>()));
        boolean isValidChain = blockchain.isChainValid();
        System.out.println("区块链是否是有效的 " + isValidChain);
    }
}
