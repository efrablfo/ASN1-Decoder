/**
 * This class file was automatically generated by jASN1 v1.9.0 (http://www.openmuc.org)
 */

package chargingcdr;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;


public class NumberOfSDPInterrogations extends BerInteger {

	private static final long serialVersionUID = 1L;

	public NumberOfSDPInterrogations() {
	}

	public NumberOfSDPInterrogations(byte[] code) {
		super(code);
	}

	public NumberOfSDPInterrogations(BigInteger value) {
		super(value);
	}

	public NumberOfSDPInterrogations(long value) {
		super(value);
	}

}