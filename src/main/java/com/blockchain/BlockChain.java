package com.blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    /**
     * 区跨链列表
     */
    private List<Block> chain;

    /**
     * 难度系数
     */
    private int difficulty;

    public BlockChain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        // 初始化区块链，添加创世区块
        chain.add(createGenesisBlock());
    }

    /**
     * 创建创世区块
     * @return
     */
    private Block createGenesisBlock() {
        List<Transaction> transactions = new ArrayList<>();
        return new Block(0, "0", transactions);
    }

    /**
     * 获取最后一个区块
     * @return
     */
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    /**
     * 添加一个区块
     * @param newBlock
     */
    public void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    /**
     * 判断是否是有效的区块链
     * @return
     */
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            // 获取当前区块
            Block currentBlock = chain.get(i);
            // 获取当前区块的上一个区块
            Block previousBlock = chain.get(i - 1);

            // 校验当前区块的hash值是否发生变化
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }

            // 校验当前区块的上一个区块hash值是否相等
            if (!previousBlock.hash.equals(currentBlock.prevBlockHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
