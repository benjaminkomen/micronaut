package benjaminkomen.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api")
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    private RxHttpClient httpClient;

    public HelloWorldController(@Client("http://httpbin.org") RxHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Get("/hello")
    public String hello() {
        logger.info("Called Hello resource.");
        return "Hello World!";
    }

    @Get("/fb")
    public Flowable<String> fb() {
        logger.info("Trying to call FB");
        logger.info("Using url http://httpbin.org/get");
        try {
            return httpClient.retrieve("/get")
                    .doOnError(it -> logger.error("Error calling fb api flowable", it))
                    .doFinally(() -> logger.info("Finished calling FB api flowable"));
        } catch (Exception ex) {
            logger.error("Error calling fb api", ex);
            throw ex;
        } finally {
            logger.info("Finished calling fb api");
        }
    }
}
