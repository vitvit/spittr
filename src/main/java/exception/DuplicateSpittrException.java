package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Spittle exists already.")
public class DuplicateSpittrException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
