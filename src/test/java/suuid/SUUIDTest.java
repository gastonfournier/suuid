package suuid;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by gastonfournier on 25/06/17.
 */
public class SUUIDTest {

    private UUID uuid = UUID.fromString("70ccb163-e1c4-4079-8f9d-4d091bf51e75");

    @Test
    public void encodeDecodeTest(){
        String hexString = uuid.toString();
        String base64String = new SUUID(uuid).toBase64();
        System.out.println(hexString + " -> " + base64String);
        assertEquals(uuid, SUUID.fromBase64(base64String).getUuid());
        assertEquals(uuid, SUUID.fromString(hexString).getUuid());
    }
}