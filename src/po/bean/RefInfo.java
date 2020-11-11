package po.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RefInfo implements Serializable {

	public RefInfo() {
	}

	private String type;
	private String name;
	private String amount;
	private String buy;
	private String note;
	private String gomi;


	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getAmount() {
		return amount;
	}
	public String getBuy() {
		return buy;
	}
	public String getNote() {
		return note;
	}
	public String getGomi() {
		return gomi;
	}
	public void setType(String type) {
		this.type = type;
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
	public void setNote(String note) {
		this.note = note;
	}
	public void setGomi(String gomi) {
		this.note = gomi;
	}


}
