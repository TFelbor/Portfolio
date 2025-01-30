# Stock Tracker Application

A JavaFX-based stock tracking application that allows users to monitor real-time stock prices using the Finnhub API. The application enables users to search for stocks, track multiple stocks simultaneously, and visualize price changes with color-coded indicators.


## Table of Contents

1. Features
2. Technical Requirements
3. Dependencies
4. Project Structure
5. Setup and Installation
6. Usage
7. File Storage
8. Limitations
9. Potential Improvements


## 1. Features

- Real-time stock price tracking
- Color-coded price change visualization (green for increase, red for decrease)
- Ability to save and track up to 5 different stocks
- Persistent storage of tracked stocks between sessions
- Simple and intuitive user interface


## 2. Technical Requirements

- Java Development Kit (JDK) 8 or higher
- JavaFX
- Internet connection for API access
- Finnhub API key


## 3. Dependencies

- JavaFX for the user interface
- org.json library for JSON parsing
- Finnhub API for stock data


## 4. Project Structure

- `projectTwoMain.java` → Main application entry point
- `RootScene.java` → Main UI scene and application logic
- `Stock.java` → Stock data model class


## 5. Setup and Installation

1. Ensure you have JDK 8 or higher installed
2. Clone the repository
3. Set up your Finnhub API key in `RootScene.java`
4. Compile and run `projectTwoMain.java`


## 6. Usage

1. **Search for a Stock**
   - Enter the stock symbol in the search field
   - Click "Find" to get the current price

2. **Track a Stock**
   - After finding a stock, click "Track" to add it to your tracking list
   - Up to 5 stocks can be tracked simultaneously

3. **Monitor Price Changes**
   - Tracked stocks are displayed on the left panel
   - Green indicates price increase
   - Red indicates price decrease

4. **Save and Exit**
   - Click "Quit" to save your tracked stocks and exit
   - Tracked stocks will be automatically loaded next time you start the application


## 7. File Storage

The application stores tracked stocks in a `saved.txt` file in the following format:
```
SYMBOL,PRICE
```


## 8. Limitations

- Maximum of 5 tracked stocks
- Requires active internet connection
- API rate limits may apply


## 9. Potential Improvements

- Additional stock information (volume, market cap, etc.)
- Graph visualization of price history
- Multiple watchlists
- Enhanced error handling
- Support for different time zones

