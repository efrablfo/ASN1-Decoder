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


public class AccountSpecifiedConsumption implements Serializable {

	private static final long serialVersionUID = 1L;

	public byte[] code = null;
	private DedicatedAccountSpecifiedConsumption dedicatedAccountSpecifiedConsumption = null;
	private MainAccountSpecifiedConsumption mainAccountSpecifiedConsumption = null;
	private SpecifiedConsumptionNoCost specifiedConsumptionNoCost = null;
	
	public AccountSpecifiedConsumption() {
	}

	public AccountSpecifiedConsumption(byte[] code) {
		this.code = code;
	}

	public void setDedicatedAccountSpecifiedConsumption(DedicatedAccountSpecifiedConsumption dedicatedAccountSpecifiedConsumption) {
		this.dedicatedAccountSpecifiedConsumption = dedicatedAccountSpecifiedConsumption;
	}

	public DedicatedAccountSpecifiedConsumption getDedicatedAccountSpecifiedConsumption() {
		return dedicatedAccountSpecifiedConsumption;
	}

	public void setMainAccountSpecifiedConsumption(MainAccountSpecifiedConsumption mainAccountSpecifiedConsumption) {
		this.mainAccountSpecifiedConsumption = mainAccountSpecifiedConsumption;
	}

	public MainAccountSpecifiedConsumption getMainAccountSpecifiedConsumption() {
		return mainAccountSpecifiedConsumption;
	}

	public void setSpecifiedConsumptionNoCost(SpecifiedConsumptionNoCost specifiedConsumptionNoCost) {
		this.specifiedConsumptionNoCost = specifiedConsumptionNoCost;
	}

	public SpecifiedConsumptionNoCost getSpecifiedConsumptionNoCost() {
		return specifiedConsumptionNoCost;
	}

	public int encode(OutputStream os) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			return code.length;
		}

		int codeLength = 0;
		if (specifiedConsumptionNoCost != null) {
			codeLength += specifiedConsumptionNoCost.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
			os.write(0xA2);
			codeLength += 1;
			return codeLength;
		}
		
		if (mainAccountSpecifiedConsumption != null) {
			codeLength += mainAccountSpecifiedConsumption.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 1
			os.write(0xA1);
			codeLength += 1;
			return codeLength;
		}
		
		if (dedicatedAccountSpecifiedConsumption != null) {
			codeLength += dedicatedAccountSpecifiedConsumption.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			os.write(0xA0);
			codeLength += 1;
			return codeLength;
		}
		
		throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
	}

	public int decode(InputStream is) throws IOException {
		return decode(is, null);
	}

	public int decode(InputStream is, BerTag berTag) throws IOException {

		int codeLength = 0;
		BerTag passedTag = berTag;

		if (berTag == null) {
			berTag = new BerTag();
			codeLength += berTag.decode(is);
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			dedicatedAccountSpecifiedConsumption = new DedicatedAccountSpecifiedConsumption();
			codeLength += dedicatedAccountSpecifiedConsumption.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
			mainAccountSpecifiedConsumption = new MainAccountSpecifiedConsumption();
			codeLength += mainAccountSpecifiedConsumption.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
			specifiedConsumptionNoCost = new SpecifiedConsumptionNoCost();
			codeLength += specifiedConsumptionNoCost.decode(is, false);
			return codeLength;
		}

		if (passedTag != null) {
			return 0;
		}

		throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(os);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		if (dedicatedAccountSpecifiedConsumption != null) {
			sb.append("dedicatedAccountSpecifiedConsumption: ");
			dedicatedAccountSpecifiedConsumption.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (mainAccountSpecifiedConsumption != null) {
			sb.append("mainAccountSpecifiedConsumption: ");
			mainAccountSpecifiedConsumption.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (specifiedConsumptionNoCost != null) {
			sb.append("specifiedConsumptionNoCost: ");
			specifiedConsumptionNoCost.appendAsString(sb, indentLevel + 1);
			return;
		}

		sb.append("<none>");
	}

}
