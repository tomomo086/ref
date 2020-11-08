package po.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RefInfo implements Serializable {

	public RefInfo() {
	}

	private String name;
	private String amount;
	private String buy;


	public String getName() {
		return this.name;
	}
	public String getAmount() {
		return this.amount;
	}
	public String getBuy() {
		return this.buy;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setBuy(String buy) {
		this.buy = buy;
	}

}
