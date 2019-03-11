package efeeney.sarafan.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Component
public class WsSender {
	private final SimpMessagingTemplate template;
	private final ObjectMapper mapper;

	public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
		this.template = template;
		this.mapper = mapper;
	}

	public <T> Consumer<T> getSender(Class view) {

		ObjectWriter writer = mapper
				.setSerializationConfig(mapper.getSerializationConfig())
				.writerWithView(view);
		return(T payload) -> {
			String value = null;
			try {
				value = writer.writeValueAsString(payload);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			template.convertAndSend(
					"/topic/activity",
					value
			);
		};
	}
}
