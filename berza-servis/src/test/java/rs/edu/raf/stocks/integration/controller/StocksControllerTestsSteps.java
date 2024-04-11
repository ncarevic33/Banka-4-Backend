package rs.edu.raf.stocks.integration.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import rs.edu.raf.stocks.dto.StockDTO;
import rs.edu.raf.stocks.response.StockHistoryInfo;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.fail;

public class StocksControllerTestsSteps extends StocksControllerConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MvcResult mvcResult;

    @When("pozovem daily history endpoint sa {string}")
    public void pozovemDailyHistoryEndpointSa(String ticker) {
        try {
            ResultActions resultActions = mockMvc.perform(get("/stock/dailyHistory/" + ticker))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Then("dobijem daily stock history podatke")
    public void dobijemDailyStockHistoryPodatke() {
        try{
            String json = mvcResult.getResponse().getContentAsString();

            Map<String, StockHistoryInfo> dailyHistory = objectMapper.readValue(json,  Map.class);
            if (dailyHistory.isEmpty()) {
                fail("Daily history is empty");
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("pozovem weekly history endpoint sa {string}")
    public void pozovemWeeklyHistoryEndpointSa(String ticker) {
        try {
            ResultActions resultActions = mockMvc.perform(get("/stock/weeklyHistory/" + ticker))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Then("dobijem weekly stock history podatke")
    public void dobijemWeeklyStockHistoryPodatke() {
        try{
            String json = mvcResult.getResponse().getContentAsString();

            Map<String, StockHistoryInfo> dailyHistory = objectMapper.readValue(json,  Map.class);
            if (dailyHistory.isEmpty()) {
                fail("Weekly history is empty");
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("pozovem monthly history endpoint sa {string}")
    public void pozovemMonthlyHistoryEndpointSa(String ticker) {
        try {
            ResultActions resultActions = mockMvc.perform(get("/stock/monthlyHistory/" + ticker))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Then("dobijem monthly stock history podatke")
    public void dobijemMonthlyStockHistoryPodatke() {
        try{
            String json = mvcResult.getResponse().getContentAsString();

            Map<String, StockHistoryInfo> dailyHistory = objectMapper.readValue(json,  Map.class);
            if (dailyHistory.isEmpty()) {
                fail("Monthly history is empty");
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("pozovem endpoint za dodavanje stocka sa tickerom {string}")
    public void pozovemEndpointZaDodavanjeStockaSaTickerom(String ticker) {
        try {
            ResultActions resultActions = mockMvc.perform(post("/stock")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .content("{\"ticker\":\"" + ticker + "\"}"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Then("stock {string} je dodat u bazu")
    public void stockJeDodatUBazu(String ticker) {
        try{
            ResultActions resultActions = mockMvc.perform(get("/stock/" + ticker))
                    .andExpect(status().isOk());

            String json = resultActions.andReturn().getResponse().getContentAsString();

            StockDTO stock = objectMapper.readValue(json,  StockDTO.class);
            if (stock == null || !stock.getTicker().equals(ticker)) {
                fail("Stock not properly added to database!");
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("pozovem endpoint za listanje svih stockova")
    public void pozovemEndpointZaListanjeSvihStockova() {
        try {
            ResultActions resultActions = mockMvc.perform(get("/stock/all"))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Then("dobijem listu svih stockova iz baze sa {string} stockom")
    public void dobijemListuSvihStockovaIzBazeSaStockom(String ticker) {
        try{
            boolean found = false;

            String json = mvcResult.getResponse().getContentAsString();

            List<StockDTO> stock = objectMapper.readValue(json, new TypeReference<List<StockDTO>>() {});
            if (stock.isEmpty()) {
                fail("No stocks in database!");
            }

            for (StockDTO stockDTO : stock) {
                if (stockDTO.getTicker().equals(ticker)) {
                    found = true;
                }
            }

            if (!found) {
                fail("Stock " + ticker + " not found in database!");
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    @When("pozovem endpoint za dohvat stocka sa tickerom {string}")
    public void pozovemEndpointZaDohvatStockaSaTickerom(String ticker) {
        try{
            ResultActions resultActions = mockMvc.perform(get("/stock/" + ticker))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("dobijem stock sa tickerom {string}")
    public void dobijemStockSaTickerom(String ticker) {
        try{
            String json = mvcResult.getResponse().getContentAsString();

            StockDTO stock = objectMapper.readValue(json,  StockDTO.class);
            if (stock == null || !stock.getTicker().equals(ticker)) {
                fail("Stock not found in database!");
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("pozovem endpoint za listanje stockova sa last refreshom {string}")
    public void pozovemEndpointZaListanjeStockovaSaLastRefreshom(String date) {
        try{
            ResultActions resultActions = mockMvc.perform(get("/stock/all/date/" + date))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("dobijem listu stockova sa last refreshom {string}")
    public void dobijemListuStockovaSaLastRefreshom(String date) {
        try{
            boolean found = false;

            String json = mvcResult.getResponse().getContentAsString();

            List<StockDTO> stock = objectMapper.readValue(json,  new TypeReference<List<StockDTO>>() {});
            if (stock.isEmpty()) {
                fail("No stocks with provided date found!");
            }

            for (StockDTO stockDTO : stock) {
                if (stockDTO.getLastRefresh().equals(date)) {
                    found = true;
                }
            }

            if (!found) {
                fail("No stocks with provided date found!");
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("pozovem endpoint za listanje stockova sa exchangeom {string}")
    public void pozovemEndpointZaListanjeStockovaSaExchangeom(String exchange) {
        try{
            ResultActions resultActions = mockMvc.perform(get("/stock/all/exchange/" + exchange))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("dobijem listu stockova sa exchangeom {string}")
    public void dobijemListuStockovaSaExchangeom(String exchange) {
        try{
            boolean found = false;

            String json = mvcResult.getResponse().getContentAsString();

            List<StockDTO> stock = objectMapper.readValue(json,  new TypeReference<List<StockDTO>>() {});
            if (stock.isEmpty()) {
                fail("No stocks with provided exchange found!");
            }

            for (StockDTO stockDTO : stock) {
                if (stockDTO.getExchange().equals(exchange)) {
                    found = true;
                }
            }

            if (!found) {
                fail("No stocks with provided exchange found!");
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @When("pozovem endpoint za brisanje stocka sa tickerom {string}")
    public void pozovemEndpointZaBrisanjeStockaSaTickerom(String ticker) {
        try{
            ResultActions resultActions = mockMvc.perform(delete("/stock/" + ticker))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("stock {string} je izbrisan iz baze")
    public void stockJeIzbrisanIzBaze(String ticker) {
        try {
            boolean found = false;

            ResultActions resultActions = mockMvc.perform(get("/stock/all"))
                    .andExpect(status().isOk());

            String json = resultActions.andReturn().getResponse().getContentAsString();

            List<StockDTO> stock = objectMapper.readValue(json,   new TypeReference<List<StockDTO>>() {});
            if (stock.isEmpty()) {
                return;
            }

            for (StockDTO stockDTO : stock) {
                if (stockDTO.getTicker().equals(ticker)) {
                    fail("Stock " + ticker + " found in database!");
                }
            }

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}


