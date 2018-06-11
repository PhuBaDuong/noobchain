package noobcoin;

import java.security.PublicKey;

public class TransactionOutput {

	public String id;
	public PublicKey reciepient;
	public float value;
	public String parentTransactionId;

	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
		super();
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
	}

	public boolean isMine(PublicKey publicKey) {
		return this.reciepient == publicKey;
	}
}
