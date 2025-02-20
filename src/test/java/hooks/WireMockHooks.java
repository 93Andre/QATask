package hooks;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class WireMockHooks {
    private static WireMockServer wireMockServer;

    @Before(order = 0)
    public void startWireMock() {
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(8080);
            wireMockServer.start();
            configureFor("localhost", 8080);

            stubFor(get(urlEqualTo("/index.html"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "text/html")
                            .withBodyFile("index.html")));

            stubFor(get(urlEqualTo("/api/movie/1084199-companion"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("data/mock-filmdata.json")));
        }
    }

    @After(order = 0)
    public void stopWireMock() {
//        if (wireMockServer != null && wireMockServer.isRunning()) {
//            wireMockServer.stop();
//        }
    }
}
