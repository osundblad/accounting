package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

/**
 * Used by JPA to convert a UUID to a String.
 */
@SuppressWarnings("unused")
@Converter(autoApply = true)
public class UUIDStringConverter implements AttributeConverter<UUID, String> {

    @Override
    @Nullable
    public String convertToDatabaseColumn(@Nullable final UUID uuid) {
    	return ((uuid == null) ? null : uuid.toString());
    }

    @Override
    @Nullable
    public UUID convertToEntityAttribute(@Nullable final String s) {
        return ((s == null) ? null : UUID.fromString(s));
    }

}
