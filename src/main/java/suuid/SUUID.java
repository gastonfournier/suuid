package suuid;

import java.util.Base64;
import java.util.UUID;

/**
 * A class that represents an immutable universally unique identifier (UUID).
 * A UUID represents a 128-bit value, extended with its base64 representation
 */
public class SUUID implements java.io.Serializable, Comparable<SUUID> {
    private static final long serialVersionUID = 3736675068350969172L;

    /*
     * The inner representation of this uuid
     * @serial
     */
    private final UUID uuid;

    /*
     * The base64 encoder used by this class to encode the uuid to its base64 representation.
     * In a holder class to defer initialization until needed.
     */
    private static class EncoderHolder {
        static final Base64.Encoder ENCODER = Base64.getUrlEncoder().withoutPadding();
    }

    /*
     * The base64 decoder used by this class to decode the uuid from its base64 representation.
     * In a holder class to defer initialization until needed.
     */
    private static class DecoderHolder {
        static final Base64.Decoder DECODER = Base64.getUrlDecoder();
    }

    // Constructors and Factories

    public SUUID(UUID uuid) {
        this.uuid = uuid;
    }

    // Delegate methods
    public SUUID(long msb, long lsb) {
        this.uuid = new UUID(msb, lsb);
    }

    public static SUUID randomSUUID() {
        return new SUUID(UUID.randomUUID());
    }

    public static SUUID fromString(String name) {
        return new SUUID(UUID.fromString(name));
    }

    public static SUUID nameUUIDFromBytes(byte[] name) {
        return new SUUID(UUID.nameUUIDFromBytes(name));
    }

    // added methods for base64 encoding and decoding

    public static SUUID fromBase64(String name) {
        byte[] decoded = DecoderHolder.DECODER.decode(name);
        long msb = 0;
        long lsb = 0;
        assert decoded.length == 16 : "data must be 16 bytes in length";
        for (int i=0; i<8; i++)
            msb = (msb << 8) | (decoded[i] & 0xff);
        for (int i=8; i<16; i++)
            lsb = (lsb << 8) | (decoded[i] & 0xff);
        return new SUUID(msb, lsb);
    }

    /**
     * Returns a {@code String} object representing this {@code SUUID}
     * in its base64 fashion
     */
    public String toBase64(){
        long msb = this.uuid.getMostSignificantBits();
        long lsb = this.uuid.getLeastSignificantBits();
        byte[] bytes = new byte[16];
        for (int i = 0; i < 8; i++){
            long byteValue = (lsb >> (i * 8)) & 0xff;
            bytes[15-i] = (byte) byteValue;
        }
        for (int i = 8; i < 16; i++){
            long byteValue = (msb >> (i * 8)) & 0xff;
            bytes[15-i] = (byte) byteValue;
        }
        return EncoderHolder.ENCODER.encodeToString(bytes);
    }

    /**
     * Unwrap uuid. Since this is not an extension of {@link java.util.UUID}
     * you may need the actual uuid in order, for example, to save it
     * into a database.
     *
     * @return The {@code UUID} value of this {@code SUUID}
     */
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return this.uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SUUID suuid = (SUUID) o;
        return this.uuid.equals(suuid.uuid);

    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

    public int compareTo(SUUID val) {
        return this.uuid.compareTo(val.uuid);
    }

    /**
     * @see {@link UUID#getLeastSignificantBits()}
     */
    public long getLeastSignificantBits(){
        return this.uuid.getLeastSignificantBits();
    }

    /**
     * @see {@link UUID#getMostSignificantBits()}
     */
    public long getMostSignificantBits(){
        return this.uuid.getMostSignificantBits();
    }
}
