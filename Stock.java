// Author: Tytus Felbor

public class Stock {
  
    // Stock attributes
    private String symbol;        // Stock symbol/ticker
    private double currentPrice;  // Current market price
    
    // Constructor with symbol only
    public Stock(String s) {
      
        this.symbol = s;
      
    }
    
    // Constructor with symbol and price
    public Stock(String s, double p) {
      
        this.symbol = s;
        this.currentPrice = p;
      
    }
    
    // Getters and setters for stock attributes
    public String getSymbol() {
      
        return symbol;
      
    }
    
    public void setSymbol(String s) {
      
        this.symbol = s;
      
    }
    
    public double getcurrentPrice() {
      
        return currentPrice;
      
    }
    
    public void setcurrentPrice(double p) {
      
        this.currentPrice = p;
      
    }
    
    public double getclosingPrice() {
      
        return currentPrice;
      
    }
    
    public void setclosingPrice(double p) {
        this.currentPrice = p;
    }
}
