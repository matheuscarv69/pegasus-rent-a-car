package src.core.domain.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StandardErrorTest {

    public static final Instant NOW = Instant.now();
    public static final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    public static final String VALIDATION_ERROR = "Validation Error";
    public static final String MESSAGE = "A error";
    public static final String PATH_OF_YOUR_DREAMS = "path of your dreams";
    public static final Map<String, String> MAP_NAME = Map.of("name", "must not be black");

    @Test
    @DisplayName("Should to create a Standard Error")
    void shouldToCreateAStandardError() {


        // cenary
        var standardErrorByBuilder = createStandardErrorByBuilder();

        var standardErrorByConstructor = new StandardError(
                NOW,
                STATUS_CODE,
                VALIDATION_ERROR,
                MESSAGE,
                PATH_OF_YOUR_DREAMS,
                MAP_NAME
        );

        var standardErrorDefault = new StandardError.StandardErrorBuilder();

        standardErrorDefault.timestamp(NOW);
        standardErrorDefault.status(STATUS_CODE);
        standardErrorDefault.error(VALIDATION_ERROR);
        standardErrorDefault.message(MESSAGE);
        standardErrorDefault.path(PATH_OF_YOUR_DREAMS);
        standardErrorDefault.errors(MAP_NAME);

        // validation
        assertEquals(NOW, standardErrorByBuilder.getTimestamp());
        assertEquals(STATUS_CODE, standardErrorByBuilder.getStatus());
        assertEquals(VALIDATION_ERROR, standardErrorByBuilder.getError());
        assertEquals(MESSAGE, standardErrorByBuilder.getMessage());
        assertEquals(PATH_OF_YOUR_DREAMS, standardErrorByBuilder.getPath());
        assertEquals(MAP_NAME, standardErrorByBuilder.getErrors());

    }

    private StandardError createStandardErrorByBuilder() {

        return StandardError
                .builder()
                .timestamp(NOW)
                .status(STATUS_CODE)
                .error(VALIDATION_ERROR)
                .message(MESSAGE)
                .path(PATH_OF_YOUR_DREAMS)
                .errors(MAP_NAME)
                .build();

    }

}