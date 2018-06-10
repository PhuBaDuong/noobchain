package impl;

import java.security.Security;
import java.util.ArrayList;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.gson.GsonBuilder;

import noobcoin.Transaction;
import noobcoin.Wallet;
import utils.StringUtils;

public class NoobChain {

	public static ArrayList<Block> blockchain = new ArrayList<>();
	public static final int DIFFICULTY = 6;
	
	public static Wallet  walletA;
	public static Wallet  walletB;

	public static void main(String[] args) {
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to mine block 1...");
		blockchain.get(0).mineBlock(DIFFICULTY);
		blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to mine block 2...");
		blockchain.get(1).mineBlock(DIFFICULTY);
		blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to mine block 3...");
		blockchain.get(2).mineBlock(DIFFICULTY);
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
		System.out.println(isChainValid());
		// --------------------------------------------
		
		
		
		Security.addProvider(new  BouncyCastleProvider());
		walletA = new Wallet();
		walletB = new Wallet();
		
		System.out.println("Private and public keys");
		System.out.println(StringUtils.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtils.getStringFromKey(walletA.publicKey));
	
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
	
		System.out.println("is signature verified : " + transaction.verifySignature());
		
	}

	public static boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[DIFFICULTY]).replace('\0', '0');

		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hashes not equal");
				return false;
			}

			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous hashes are not equal");
				return false;
			}

			// check if hash is solved
			if (!currentBlock.hash.substring(0, DIFFICULTY).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
