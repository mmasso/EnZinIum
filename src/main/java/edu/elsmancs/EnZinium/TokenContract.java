package edu.elsmancs.EnZinium;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private Address owner = null;
    private PublicKey ownerPK = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0.0;
    private final Map<PublicKey, Double> balances = new HashMap<>(); 
    private Double totalTokensSold = 0d;
    private Double tokenPrice = 0d;

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

    /**
     * @return the owner
     */
    public PublicKey getOwnerPK() {
        return ownerPK;
    }

    /**
     * @return the tokenPrice
     */
    public Double getTokenPrice() {
        return tokenPrice;
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


    public void transfer(PublicKey destinatario, Double tokens) {
        try {
            require(balanceOf(ownerPK) >= tokens);
            this.getBalances().compute(ownerPK, (pk, token) -> token - tokens);
            this.getBalances().put(destinatario, balanceOf(destinatario) + tokens);
        } catch (Exception e) {
            /* pues nada */
        }      
    }

    private void require(Boolean holds) throws Exception {
        if (! holds) {
            throw new Exception();
        }
    }

    public void owners() {
        for (PublicKey pk : this.getBalances().keySet()) {
            if (!pk.equals(this.ownerPK)) {
                System.out.println("Owner: " + pk.hashCode() + " " 
                                             + getBalances().get(pk) + " "
                                             + this.getSymbol());
            }
        }
    }

    public int totalTokensSold() {
        this.getBalances().forEach((pk, units) -> this.totalTokensSold += units);
        this.totalTokensSold -= balanceOf(ownerPK);
        return this.totalTokensSold.intValue();
    }

    void payable(PublicKey recipient, Double enziniums) {
        try {
            require(enziniums >= this.getTokenPrice());
            Double units = Math.floor(enziniums / tokenPrice);
            transfer(recipient, units);
            this.owner.transferEZI(enziniums);
        } catch (Exception e) {
            // fail silently
        }
    }

    public void transfer(PublicKey sender, PublicKey recipient, Double units) {
        try {
            require(balanceOf(sender) >= units);
            this.getBalances().put(sender, balanceOf(sender) - units);
            this.getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (Exception e) {
            // fails silently
        }   
    }

    @Override
    public String toString() {
        return "name " + getName() + "\n"
                + "symbol " + getSymbol() + "\n" 
                + "totalSupply " + getTotalSupply() + "\n"
                + "owner PK " + getOwnerPK().hashCode() + "\n";
    }
}
