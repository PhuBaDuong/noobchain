package noobcoin;

public class TransactionInput {

	public String transactionOutputId;
	public TransactionOutput UTXO;

	public TransactionInput(String transactionOutputId) {
		super();
		this.transactionOutputId = transactionOutputId;
	}

}
