package impl;

import java.util.Date;

import utils.StringUtils;

public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;

	public Block(String data, String previousHash) {
		super();
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		return StringUtils.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
	}
	
	public void mineBlock(int difficulty)
	{
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block mined!!! : " + hash);
	}
}
