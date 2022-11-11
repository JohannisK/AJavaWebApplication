package proof.of.concept.ajavawebapplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

  private static final HttpClient httpClient = HttpClient.newBuilder()
      .version(HttpClient.Version.HTTP_1_1)
      .connectTimeout(Duration.ofSeconds(10))
      .build();

  @GetMapping("/")
  public String hello() {
    return "Hello";
  }

  @PostConstruct
  public void post() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("http://localhost:9051/STARTED"))
        .build();

    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
  }
}