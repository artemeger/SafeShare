
import jetbrains.exodus.util.HexUtil;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class TestTest {

    @Test
    public void test() {
        long l1 = 1L;
        long l2 = 0L;
        long l3 = 11L;
        long l4 = 987421479234722347L;
        long l5 = 32141L;
        int i = 1735893743;
        String s = HexUtil.byteArrayToString(ByteBuffer.allocate(8).putLong(l4).array());
        String si = HexUtil.byteArrayToString(ByteBuffer.allocate(4).putInt(i).array());
        System.out.println(HexUtil.byteArrayToString(ByteBuffer.allocate(8).putLong(l1).array()));
        System.out.println(HexUtil.byteArrayToString(ByteBuffer.allocate(8).putLong(l2).array()));
        System.out.println(HexUtil.byteArrayToString(ByteBuffer.allocate(8).putLong(l3).array()));
        System.out.println(HexUtil.byteArrayToString(ByteBuffer.allocate(8).putLong(l4).array()));
        System.out.println(HexUtil.byteArrayToString(ByteBuffer.allocate(8).putLong(l5).array()));
        System.out.println(ByteBuffer.wrap(HexUtil.stringToByteArray(s)).getLong());
        System.out.println(si);
        System.out.println(ByteBuffer.wrap(HexUtil.stringToByteArray(si)).getInt());
    }
}