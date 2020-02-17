package edu.elsmancs.EnZinium;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private Address owner = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0.0;
    private Double tokenPrice = 0.0; 
    private final Map<PublicKey, Double> balances = new HashMap<>(); 

	public TokenContract(Address owner) {
        this.owner = owner;
	}

	public void setName(String name) {
        this.name = name;
    }
    
	public void setSymbol(String symbol) {
        this.symbol = symbol;
	}

	public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
	}

	public void setTokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return the totalSupply
     */
    public double getTotalSupply() {
        return totalSupply;
    }
    
    /**
     * @return the balances
     */
    public Map<PublicKey, Double> getBalances() {
        return balances;
    }

    public void addOwner(PublicKey PK, Double units) {
        getBalances().putIfAbsent(PK, units);
    }

    public int numOwners() {
        return this.getBalances().size();
    }
    

    public Double balanceOf(PublicKey owner){
        return this.getBalances().getOrDefault(owner, 0.0);
    }

    @Override
    public String toString() {
        return "name " + getName() + "\n"
                + "symbol " + getSymbol() + "\n" 
                + "totalSupply " + getTotalSupply() + "\n"
                + "owner PK " + owner.getPK().hashCode() + "\n";
    }

}
