
public final class GaloisDM
	extends Galois {
	public static final int POLYNOMIAL = 0x2d;		// x^8 + x^5 + x^3 + x^2 + 1
	private static final Galois instance = new GaloisDM();

	private GaloisDM() {
		super(POLYNOMIAL, 1);
	}

	public static Galois getInstance() {
		return instance;
	}
}
