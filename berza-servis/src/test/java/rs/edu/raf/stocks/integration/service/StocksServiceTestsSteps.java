package rs.edu.raf.stocks.integration.service;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import rs.edu.raf.stocks.dto.StockDTO;
import rs.edu.raf.stocks.dto.TickerDTO;
import rs.edu.raf.stocks.exceptions.StockAlreadyExistsException;
import rs.edu.raf.stocks.response.StockHistoryInfo;
import rs.edu.raf.stocks.service.StockService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class StocksServiceTestsSteps extends StocksServiceConfig {

    @Autowired
    private StockService stockService;

    //First, third, fourth, fifth scenario return
    List<StockDTO> stocksDTO;

    //Second scenario return
    StockDTO ibmStockDTO;

    String lastRefresh;

    Map<String, StockHistoryInfo> dailyHistory;

    Map<String, StockHistoryInfo> weeklyHistory;

    Map<String, StockHistoryInfo> monthlyHistory;

    @Given("da su dodati stocks {string}, {string}")
    public void dodajuSeStocks(String string1, String string2) {
        TickerDTO ibmTicker = new TickerDTO();
        ibmTicker.setTicker(string1);
        TickerDTO aaplTicker = new TickerDTO();
        aaplTicker.setTicker(string2);
        try {
            stockService.addStock(ibmTicker);
            stockService.addStock(aaplTicker);
        }
        catch (StockAlreadyExistsException e){
            //sve u redu vec smo dodali pre stockove u bazu
            //pa se zato buni
        }

    }

    @When("zatrazim sve stocks")
    public void zatrazimSveStocks() {
        stocksDTO = stockService.getAllStocks();
    }

    @Then("dobijem listu sa stocks {string}, {string}")
    public void dobijemListuSaStocksIBMAPPL(String string1, String string2) {
        boolean foundFirst = false;
        boolean foundSecond = false;

        for (StockDTO stock : stocksDTO){
            if (stock.getTicker().equals(string1))
                foundFirst = true;
            if (stock.getTicker().equals(string2))
                foundSecond = true;
        }

        if (!foundFirst || !foundSecond)
            fail("ne postoje stocks IBM, APPL u bazi");
    }

    @Given("postoji {string} stock u bazi")
    public void postojiStockUBazi(String ticker) {
        // IBM stock je vec u bazi od prethodnog scenarija
    }

    @When("zatrazim stock sa tickerom {string}")
    public void zatrazimStockSaTickerom(String ticker) {
        ibmStockDTO = stockService.getStockByTicker(ticker);
    }

    @Then("dobijem stock sa tickerom {string}")
    public void dobijemStockSaTickerom(String ticker) {
        if (!ibmStockDTO.getTicker().equals(ticker))
            fail("nije pronadjen stock sa tickerom IBM");
    }

    @Given("postoji stock sa nekim last refreshom u bazi")
    public void postojiStockSaNekimLastRefreshomUBazi() {
        //Vec imamo neke stockove u bazi od prethodnih testova
        lastRefresh = stockService.getStockByTicker("IBM").getLastRefresh();
    }

    @When("zatrazim stock sa tim last refreshom")
    public void zatrazimStockSaTimLastRefreshom() {
        stocksDTO = stockService.getStocksByLastRefresh(lastRefresh);
    }

    @Then("dobijem stockove sa tim last refreshom")
    public void dobijemStockoveSaTimLastRefreshom() {
        boolean found = false;

        for (StockDTO stock : stocksDTO){
            if (stock.getLastRefresh().equals(lastRefresh))
                found = true;
            if (!stock.getLastRefresh().equals(lastRefresh))
                fail("postoji stock sa last refreshom drugacijim od prosledjenog " + lastRefresh);
        }

        if (!found)
            fail("ne postoje stockovi sa last refreshom " + lastRefresh);
    }

    @Given("postoji stock sa exchangeom {string} u bazi")
    public void postojiStockSaExchangeomUBazi(String exchange) {
        // Imamo u bazi IBM koji je sa NYSE exchangeom od prethodnih testova
    }

    @When("zatrazim stock sa exchangeom {string}")
    public void zatrazimStockSaExchangeom(String exchange) {
        stocksDTO = stockService.getStocksByExchange(exchange);
    }

    @Then("dobijem stockove sa exchangeom {string}")
    public void dobijemStockoveSaExchangeom(String exchange) {
        boolean found = false;

        for (StockDTO stock : stocksDTO){
            if (stock.getExchange().equals(exchange))
                found = true;
            if (!stock.getExchange().equals(exchange))
                fail("postoji stock sa exchangeom drugacijim od prosledjenog " + exchange);
        }

        if (!found)
            fail("ne postoje stockovi sa exchangeom " + exchange);
    }

    @Given("da postoji stock {string} na apiju")
    public void daPostojiStockNaApiju(String ticker) {
        // AL postoji kao ticker na apiju
    }

    @When("dodam stock {string} sa apija")
    public void dodamStockSaApija(String ticker) {
        TickerDTO alTickerDTO = new TickerDTO();
        alTickerDTO.setTicker(ticker);
        stockService.addStock(alTickerDTO);
    }

    @Then("stock {string} je dodat u bazu")
    public void stockJeDodatUBazu(String ticker) {
        StockDTO stockFound = stockService.getStockByTicker(ticker);

        if (stockFound == null)
            fail("stock " + ticker + " nije dodat u bazu");
    }

    @Given("da postoji stock {string} u bazi")
    public void daPostojiStockUBazi(String ticker) {
        // Postoji AL stock od prethodnog scenarija
    }

    @When("obrisem stock {string} iz baze")
    public void obrisemStockIzBaze(String ticker) {
        stockService.deleteStock(ticker);
    }

    @Then("stock {string} je obrisan iz baze")
    public void stockJeObrisanIzBaze(String ticker) {
        StockDTO stockFound = stockService.getStockByTicker(ticker);

        if (stockFound != null)
            fail("stock " + ticker + " nije obrisan iz baze");
    }

    @When("zatrazim dailyStockHistory za stock {string}")
    public void zatrazimDailyStockHistoryZaStock(String ticker) {
        dailyHistory = stockService.getDailyStockHistory(ticker);
    }

    @Then("dobijem dailyStockHistory za stock {string}")
    public void dobijemDailyStockHistoryZaStock(String ticker) {
        if (dailyHistory.isEmpty())
            fail("nije dobijen daily stock history za stock " + ticker);
    }

    @When("zatrazim weeklyStockHistory za stock {string}")
    public void zatrazimWeeklyStockHistoryZaStock(String ticker) {
        weeklyHistory = stockService.getWeeklyStockHistory(ticker);
    }

    @Then("dobijem weeklyStockHistory za stock {string}")
    public void dobijemWeeklyStockHistoryZaStock(String ticker) {
        if (weeklyHistory.isEmpty())
            fail("nije dobijen weekly stock history za stock " + ticker);
    }

    @When("zatrazim monthlyStockHistory za stock {string}")
    public void zatrazimMonthlyStockHistoryZaStock(String ticker) {
        monthlyHistory = stockService.getMonthlyStockHistory(ticker);
    }

    @Then("dobijem monthlyStockHistory za stock {string}")
    public void dobijemMonthlyStockHistoryZaStock(String ticker) {
        if (monthlyHistory.isEmpty())
            fail("nije dobijen monthly stock history za stock " + ticker);
    }
}
