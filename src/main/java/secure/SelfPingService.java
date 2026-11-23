package secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SelfPingService {

	private static final Logger logger = LoggerFactory.getLogger(SelfPingService.class);

	private final RestClient restClient;
	private final String selfPingUrl;

	public SelfPingService(RestClient.Builder restClientBuilder, @Value("${self-ping.url}") String selfPingUrl) {
		this.restClient = restClientBuilder.build();
		this.selfPingUrl = selfPingUrl;
	}

	@Scheduled(fixedRate = 840_000) // every 14 minutes
	public void pingSelf() {
		try {
			logger.info("Pinging self at URL: {}", selfPingUrl);

			restClient.get().uri(selfPingUrl).retrieve().toBodilessEntity(); 
																				

			logger.info("Ping successful.");
		} catch (Exception e) {
			logger.error("Ping failed", e);
		}
	}
}