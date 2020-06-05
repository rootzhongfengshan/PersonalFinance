package net.vv2.personal.finance.domain;

public class TConsumeTypeCategory {
	
	private Long Id;
	private String consume_type;
	private String consume_category;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getConsume_type() {
		return consume_type;
	}
	public void setConsume_type(String consume_type) {
		this.consume_type = consume_type;
	}
	public String getConsume_category() {
		return consume_category;
	}
	public void setConsume_category(String consume_category) {
		this.consume_category = consume_category;
	}
	
	

}
