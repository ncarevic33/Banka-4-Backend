package rs.edu.raf.berza.opcija.servis.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.edu.raf.berza.opcija.model.OpcijaTip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonParserUtil {


    private static ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger log = LoggerFactory.getLogger(JsonParserUtil.class.getSimpleName());


    //json kao bajtovi iz http body
    public static List<OptionYahooApiMap> parseBytesToOptionObject(InputStream inputStream, String ticker) throws IOException {


        List<OptionYahooApiMap> parsedOptions = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonResponse = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
            jsonResponse.append(line);//u obliku json stringa

        objectMapper = new ObjectMapper();

        //JsonNode je json objekat iz json stringa
        JsonNode mainNode = objectMapper.readTree(jsonResponse.toString());

        if(mainNode == null)
            return parsedOptions;
        //log.info(ticker);
        //optionChain->options->calls
        //optionChain->options->puts
        JsonNode optionChain = mainNode.get("optionChain");

        if(optionChain == null)
            return parsedOptions;
        JsonNode resultArr = optionChain.get("result");

        if(resultArr == null)
            return parsedOptions;

        JsonNode resultArrObj = resultArr.get(0);

        if(resultArrObj == null)
            return parsedOptions;
        //log.info(objectMapper.writeValueAsString(resultArr));
        JsonNode optionsArr = resultArrObj.get("options");

        if(optionsArr == null)
            return parsedOptions;

        JsonNode optionsArrObj = optionsArr.get(0);

        if(optionsArrObj == null)
            return parsedOptions;

        JsonNode callsOptionsArr = optionsArrObj.get("calls");
        JsonNode putsOptionsArr = optionsArrObj.get("puts");

        //System.out.println(String.valueOf(callsOptionsArr));

        if (callsOptionsArr.isArray()) {
            for (JsonNode callOpcija : callsOptionsArr) {

                objectMapper.registerModule(new JavaTimeModule());
                OptionYahooApiMap deserializedCallOption = objectMapper.readValue(objectMapper.writeValueAsString(callOpcija), OptionYahooApiMap.class);
                deserializedCallOption.setOpcijaTip(OpcijaTip.CALL);
                deserializedCallOption.setTicker(ticker);
                parsedOptions.add(deserializedCallOption);
            }
        }
        if (putsOptionsArr.isArray()) {
            for (JsonNode putsOpcija : putsOptionsArr) {

                objectMapper.registerModule(new JavaTimeModule());
                OptionYahooApiMap deserializedPutsOption = objectMapper.readValue(objectMapper.writeValueAsString(putsOpcija), OptionYahooApiMap.class);
                deserializedPutsOption.setOpcijaTip(OpcijaTip.PUT);
                deserializedPutsOption.setTicker(ticker);
                parsedOptions.add(deserializedPutsOption);
            }
        }

        return parsedOptions;

    }

    public static List<String> parseBytesToTickerNames(InputStream inputStream) throws IOException {

        List<String> tickerNames = new ArrayList<>();


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonResponse = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
            jsonResponse.append(line);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode mainNode = objectMapper.readTree(jsonResponse.toString());

        if(mainNode == null)
            return tickerNames;

        JsonNode tickersArr = mainNode.get("results");

        if(tickersArr == null)
            return tickerNames;

        if (tickersArr.isArray()) {
            for (JsonNode ticker : tickersArr) {
                JsonNode tickerName = ticker.get("ticker");
                objectMapper.registerModule(new JavaTimeModule());
                String tickerNameStr = objectMapper.readValue(objectMapper.writeValueAsString(tickerName), String.class);
                tickerNames.add(tickerNameStr);
            }

        }
        return tickerNames;
    }
}