package ec.dev.samagua.ntt_data_challenge_accounts.utils_config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

public class JsonDateSerializer extends JsonSerializer<LocalDate> {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    public void serialize(final LocalDate date, final JsonGenerator gen, final SerializerProvider provider) throws IOException, JsonProcessingException {

        String dateString = format.format(date);
        gen.writeString(dateString);
    }

}
