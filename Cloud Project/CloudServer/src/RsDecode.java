import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RsDecode {
	public static final int RS_CORRECT_ERROR = -2;
	private Galois galois = GaloisQR.getInstance();
	private int npar;

	public RsDecode(Galois galois, int npar) {
		this.galois = galois;
		this.npar = npar;
		if(npar <= 0 || npar >= 128) {
			throw new IllegalArgumentException("bad npar");
		}
	}

	public RsDecode(int npar) {
		this.galois = GaloisQR.getInstance();
		this.npar = npar;
		if(npar <= 0 || npar >= 128) {
			throw new IllegalArgumentException("bad npar");
		}
	}

	/**
	 * Calculates Sigma(z), Omega(z) from Syndrome
	 * (Modified Berlekamp-Massey Algorithm)
	 *
	 * @param syn int[]
	 *		syndrome
	 *		s0,s1,s2, ... s<npar-1>
	 * @return int[]
	 *		null: fail
	 *		int[]: sigma(z)
	 */
	public int[] calcSigmaMBM(int[] syn) {
		int[] sg0 = new int[npar+ 1];
		int[] sg1 = new int [npar + 1];
		int[] wk = new int [npar + 1];
		sg0[1] = 1;
		sg1[0] = 1;
		int jisu0 = 1;
		int jisu1 = 0;
		int m = -1;

		for(int n = 0; n < npar; n++) {
			int d = syn[n];
			for(int i = 1; i <= jisu1; i++) {
				d ^= galois.mul(sg1[i], syn[n - i]);
			}
			if(d != 0) {
				final int logd = galois.toLog(d);
				for(int i = 0; i <= n; i++) {
					wk[i] = sg1[i] ^ galois.mulExp(sg0[i], logd);
				}
				final int js = n - m;
				if(js > jisu1) {
					for(int i = 0; i <= jisu0; i++) {
						sg0[i] = galois.divExp(sg1[i], logd);
					}
					m = n - jisu1;
					jisu1 = js;
					jisu0 = js;
				}
				int[] tmp = sg1;
				sg1 = wk;
				wk = tmp;
			}
			System.arraycopy(sg0, 0, sg0, 1, jisu0);
			sg0[0] = 0;
			jisu0++;
		}
		if(sg1[jisu1] == 0) {
			return null;
		}
		int[] sigma = new int[jisu1 + 1];
		System.arraycopy(sg1, 0, sigma, 0, jisu1 + 1);
		return sigma;
	}

	private int[] chienSearch(int length, int start, int wa, int seki) {
		for(int i = start; i < length; i++) {
			final int z0 = galois.toExp(i);
			final int z1 = wa ^ z0;
			if(galois.mulExp(z1, i) == seki) {
				int idx = galois.toLog(z1);
				if(idx <= i || idx >= length) {
					return null;
				}
				return new int[] {z1, z0};
			}
		}
		return null;
	}

	/**
	 * Calculates Error Location(s)
	 * (Chien Search Algorithm)
	 *
	 * @param length int
	 *		data length (with parity)
	 * @param sigma int[]
	 * @return int
	 *		null: fail
	 *		int[] error locations
	 */
	private int[] chienSearch(int length, int[] sigma) {
		final int jisu = sigma.length - 1;
		int wa = sigma[1];
		int seki = sigma[jisu];
		if(jisu == 1) {
			if(galois.toLog(wa) >= length) {
				return null;
			}
			return new int[] {wa};
		}
		if(jisu == 2) {
			return chienSearch(length, 0, wa, seki);
		}

		int[] pos = new int[jisu];
		int posIdx = jisu - 1;
		for(int i = 0, z = 255; i < length; i++, z--) {
			int wk = 1;
			for(int j = 1, wz = z; j <= jisu; j++, wz = (wz + z) % 255) {
				wk ^= galois.mulExp(sigma[j], wz);
			}
			if(wk == 0) {
				int pv = galois.toExp(i);
				wa ^=  pv;
				seki = galois.div(seki, pv);
				pos[posIdx--] = pv;
				if(posIdx == 1) {
					int[] pos2 = chienSearch(length, i + 1, wa, seki);
					if(pos2 == null) {
						return null;
					}
					pos[0] = pos2[0];
					pos[1] = pos2[1];
					return pos;
				}
			}
		}
		return null;
	}

	/**
	 * Calculates Error Magnitude(s) and Corrects Error(s)
	 * (Forney Algorithm)
	 *
	 * @param data int[]
	 * @param length int
	 *		data length (with parity)
	 * @param pos int[]
	 *		error locations
	 * @param sigma int[]
	 * @param omega int[]
	 */
	private void doForney(int[] data, int length, int[] pos, int[] sigma, int[] omega) {
		for(int ps: pos) {
			final int zlog = 255 - galois.toLog(ps);
			int ov = galois.calcOmegaValue(omega, zlog);
			int dv = galois.calcSigmaDashValue(sigma, zlog);
			data[galois.toPos(length, ps)] ^= galois.divExp(galois.div(ov, dv), zlog);
		}
	}

	/**
	 * Decoding ReedSolomon Code
	 *
	 * @param data int[]
	 *		input data
	 * @param length int
	 *		data length (with parity)
	 * @param noCorrect boolean
	 *		error check only
	 * @return int
	 *		0: has no error
	 *		> 0: # of corrected
	 *		< 0: fail
	 */
	public int decode(int[] data, int length, boolean noCorrect) {
		if(length < npar || length > 255) {
			throw new IllegalArgumentException("bad data length:" + length);
		}
		int[] syn = new int[npar];
		if(galois.calcSyndrome(data, length, syn)) {
			return 0;
		}

		int[] sigma = calcSigmaMBM(syn);
		if(sigma == null) {
			return RS_CORRECT_ERROR;
		}

		int[] pos = chienSearch(length, sigma);
		if(pos == null) {
			return RS_CORRECT_ERROR;
		}

		if(!noCorrect) {
			int[] omega = galois.mulPoly(syn, sigma, sigma.length - 1);
			doForney(data, length, pos, sigma, omega);
		}
		return sigma.length - 1;
	}

	public int decode(int[] data, int length) {
		return decode(data, length, false);
	}

	public int decode(int[] data, boolean noCorrect) {
		return decode(data, data.length, noCorrect);
	}

	public int decode(int[] data) {
		return decode(data, data.length, false);
	}


	public static void main(String[] args) throws Exception
        {
            
            
              /*
                java.util.Scanner sin = new Scanner(System.in);
                System.out.println("Enter File Path");
                String filePath = sin.next();                
                
                System.out.println("Enter No Of Servers");
                int n = sin.nextInt();
                
                FileInputStream fin = new FileInputStream(filePath);
                int length = fin.available();
                int additionalBits = length%n;              
                int newLength = length + additionalBits;                
                byte data[] = new byte [ fin.available()];
                fin.read(data);
                fin.close();
                int newData[] = new int[newLength];
                for ( int i =  0 ;i < length ; i++ )
                    newData[i] = data[i];
                for ( int i = length ; i < newLength ; i++)
                    newData[i] = 0;                               
                                
                int rdata[] = new int[newLength*2];
                for ( int i = 0 ; i < newLength ; i++ )
                    rdata[i] = newData[i];              
              
		RsEncode enc = new RsEncode(newLength);
		enc.encode(rdata);

                  
                String fileName = filePath.substring( filePath.indexOf("/")+1 , filePath.lastIndexOf("."));                
                System.out.println(fileName);
                
                int blocksize = newLength*2 / n ;                
                
                for ( int i = 1 , k  = 0 ; i <= n ; i++ )
                {
                    String newFileName = fileName + i;
                    
                    int  block[] = new int [blocksize];
                    for ( int j = 0 ; j < blocksize ; j++  )
                        block[j] = rdata[k++];
                    
                    //java.util.ArrayList<int[]> t = new ArrayList<int[]>();
                    //t.add(block);
                    ObjectOutputStream fout = new ObjectOutputStream (new FileOutputStream("c://xxx//"+newFileName));
                    fout.writeObject(block);
                    fout.close();
                    
                }
                
                */
              
                
            
                int length = 22 , newLength = 24 , n = 4 ;                
                int rdata[] = new int [ newLength * 2 ];
                String fileName = "demo";
                int blocksize = newLength*2 / n ;                
                
                for ( int i = 1 , k  = 0 ; i <= n-1 ; i++ )
                {
                    String newFileName = fileName + i;
                    ObjectInputStream fin = new ObjectInputStream(new FileInputStream("c://xxx//"+newFileName));                   
                    int block[] =(int[]) fin.readObject();                      
                    fin.close();                    
                    for ( int j = 0 ; j < blocksize ; j++  )
                        rdata[k++] = block[j];                    
                    
                }
                             
                
                
                
		

		// Decoding QR-CODE
		RsDecode dec = new RsDecode(newLength);
		int r = dec.decode(rdata);
                
		System.out.println("r=" + r);
		System.out.println("qrData=" + java.util.Arrays.toString(rdata));

                
                /*
		// Encoding Data Matrix Barcode
		int[] dmData = new int[ref.length + 17];
		System.arraycopy(ref, 0, dmData, 0, ref.length);
		RsEncode encDM = new RsEncode(GaloisDM.getInstance(), 17);
		encDM.encode(dmData);
		System.out.println("dmData=" + java.util.Arrays.toString(dmData));
		// parity = 41, 41, 15, 250, 121, 58, 190, 205, 164, 103, 63, 149, 42, 247, 1, 214, 148

		// force add errors
		dmData[0] ^= 0x55;
		dmData[1] ^= 0xaa;

		// Decoding Data Matrix Barcode
		RsDecode decDM = new RsDecode(GaloisDM.getInstance(), 17);
		r = decDM.decode(dmData);
		System.out.println("r=" + r);
		System.out.println("dmData=" + java.util.Arrays.toString(dmData));
                * 
                */
	}

}
