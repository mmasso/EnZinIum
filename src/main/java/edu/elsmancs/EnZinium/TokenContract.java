package edu.elsmancs.EnZinium;

public class TokenContract {

    private String name = null;
    private String symbol = null;
    private double totalSupply = 0.0;
    private double tokenPrice = 0.0; 

	public void setName(String name) {
        this.name = name;
    }
    
	public void setSymbol(String symbol) {
        this.symbol = symbol;
	}

	public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
	}

	public void setTokenPrice(double tokenPrice) {
        this.tokenPrice = tokenPrice;
	}

}
