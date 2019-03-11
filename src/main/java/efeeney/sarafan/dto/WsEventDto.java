package efeeney.sarafan.dto;

import efeeney.sarafan.domain.Views;
import javafx.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.type.ObjectType;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEventDto {
	private ObjectType objectType;
	private EventType eventType;
	@JsonRawValue
	private String body;
}
