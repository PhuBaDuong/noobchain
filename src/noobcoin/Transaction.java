package noobcoin;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

import utils.StringUtils;

public class Transaction {

	public String transactionId;
	public PublicKey sender;
	public PublicKey reciepient;
	public float value;
	public byte[] signature;

	public ArrayList<TransactionInput> inputs = new ArrayList<>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<>();

	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}

	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtils.getStringFromKey(sender) + StringUtils.getStringFromKey(reciepient)
				+ Float.toString(value);
		signature = StringUtils.applyECDSASig(privateKey, data);
	}

	public boolean verifySignature() {
		String data = StringUtils.getStringFromKey(sender) + StringUtils.getStringFromKey(reciepient)
				+ Float.toString(value);
		return StringUtils.verifyECDSASig(sender, data, signature);
	}
}
