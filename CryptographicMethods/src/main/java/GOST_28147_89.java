import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class GOST_28147_89 {
	private final static byte table[][] = {
			{4, 10, 9, 2, 13, 8, 0, 14, 6, 11, 1, 12, 7, 15, 5, 3},
			{14, 11, 4, 12, 6, 13, 15, 10, 2, 3, 8, 1, 0, 7, 5, 9},
			{5, 8, 1, 13, 10, 3, 4, 2, 14, 15, 12, 7, 6, 0, 9, 11},
			{7, 13, 10, 1, 0, 8, 9, 15, 14, 4, 6, 12, 11, 2, 5, 3},
			{6, 12, 7, 1, 5, 15, 13, 8, 4, 10, 9, 14, 0, 3, 11, 2},
			{4, 11, 10, 0, 7, 2, 1, 13, 3, 6, 8, 5, 9, 12, 15, 14},
			{13, 11, 4, 1, 3, 15, 5, 9, 0, 10, 14, 7, 6, 8, 2, 12},
			{1, 15, 13, 0, 5, 7, 10, 4, 9, 2, 3, 14, 6, 11, 8, 12}
	};
	
	private byte key[][] = {
			{0,1,2,3},
			{1,2,3,0},
			{2,3,4,5},
			{3,4,5,6},
			{4,5,6,7},
			{5,6,7,0},
			{6,7,0,1},
			{7,0,1,2}
	};
	
	private final static int[] keyMap = {0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7};
	
	public void rpz(Mode mode, DataOutputStream dos, DataInputStream dis) throws Exception {
		byte[] data = new byte[8];
		int count = dis.read(data);
		while (count != -1) {
			if (count > 0) {
				for (int i = count; i < 8; i++) {
					data[i] = 0x0f;
				}
			}
			byte[] A = new byte[4];
			byte[] B = new byte[4];
			System.arraycopy(data, 0, B, 0, 4);
			System.arraycopy(data, 4, A, 0, 4);
			
			for (int k = 0; k < 32; k++) {
				byte[] K = Mode.ENCRYPT.equals(mode) ? key[keyMap[k]] : key[keyMap[31 - k]];
				int buf = ByteBuffer.wrap(A).getInt() + ByteBuffer.wrap(K).getInt();
				buf &= 0xffffffff; // A + K (mod 2^32)
				int[] s = {
						(buf & 0xF0000000) >>> 28,
						(buf & 0x0F000000) >>> 24,
						(buf & 0x00F00000) >>> 20,
						(buf & 0x000F0000) >>> 16,
						(buf & 0x0000F000) >>> 12,
						(buf & 0x00000F00) >>> 8,
						(buf & 0x000000F0) >>> 4,
						(buf & 0x0000000F)
				};
				buf = 0x00000000;
				for (int b = 0; b < 8; b++) {
					buf <<= 4;
					buf += table[b][s[b] & 0x0000000f];
				}
				buf = ((buf << 11) | (buf >>> 21));
				byte[] resBytes = ByteBuffer.allocate(4).putInt(buf).array();
				byte[] newB = {0x00, 0x00, 0x00, 0x00};
				
				System.arraycopy(A, 0, newB, 0, 4);
				for (int b = 0; b < 4; b++) {
					A[b] = (byte) (resBytes[b] ^ B[b]);
				}
				System.arraycopy(newB, 0, B, 0, 4);
			}
			
			print(dos, A, mode);
			print(dos, B, mode);
			
			count = dis.read(data);
			
		}
		dis.close();
		dos.close();
	}
	
	private void print(DataOutputStream dos, byte[] A, Mode mode) throws IOException {
		for (int i = 0; i < 4; i++) {
			if (mode.equals(Mode.DECRYPT) && A[i] != 0x0f) {
				dos.write(A, i, 1);
			} else if (mode.equals(Mode.ENCRYPT)){
				dos.write(A, 0, A.length);
				break;
			}
		}
	}
	
	public enum Mode {
		ENCRYPT,
		DECRYPT
	}
}
