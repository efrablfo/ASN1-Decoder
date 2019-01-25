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


public class ProductFeeDedicatedAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	private DedicatedAccountID dedicatedAccountID = null;
	private MonetaryUnits dedicatedAccountChange = null;
	private MonetaryUnits dedicatedAccountValueAfter = null;
	private DedicatedAccountCampaignID dedicatedAccountCampaignID = null;
	
	public ProductFeeDedicatedAccount() {
	}

	public ProductFeeDedicatedAccount(byte[] code) {
		this.code = code;
	}

	public void setDedicatedAccountID(DedicatedAccountID dedicatedAccountID) {
		this.dedicatedAccountID = dedicatedAccountID;
	}

	public DedicatedAccountID getDedicatedAccountID() {
		return dedicatedAccountID;
	}

	public void setDedicatedAccountChange(MonetaryUnits dedicatedAccountChange) {
		this.dedicatedAccountChange = dedicatedAccountChange;
	}

	public MonetaryUnits getDedicatedAccountChange() {
		return dedicatedAccountChange;
	}

	public void setDedicatedAccountValueAfter(MonetaryUnits dedicatedAccountValueAfter) {
		this.dedicatedAccountValueAfter = dedicatedAccountValueAfter;
	}

	public MonetaryUnits getDedicatedAccountValueAfter() {
		return dedicatedAccountValueAfter;
	}

	public void setDedicatedAccountCampaignID(DedicatedAccountCampaignID dedicatedAccountCampaignID) {
		this.dedicatedAccountCampaignID = dedicatedAccountCampaignID;
	}

	public DedicatedAccountCampaignID getDedicatedAccountCampaignID() {
		return dedicatedAccountCampaignID;
	}

	public int encode(OutputStream os) throws IOException {
		return encode(os, true);
	}

	public int encode(OutputStream os, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			if (withTag) {
				return tag.encode(os) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		if (dedicatedAccountCampaignID != null) {
			codeLength += dedicatedAccountCampaignID.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 3
			os.write(0x83);
			codeLength += 1;
		}
		
		if (dedicatedAccountValueAfter != null) {
			codeLength += dedicatedAccountValueAfter.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
			os.write(0xA2);
			codeLength += 1;
		}
		
		if (dedicatedAccountChange != null) {
			codeLength += dedicatedAccountChange.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 1
			os.write(0xA1);
			codeLength += 1;
		}
		
		codeLength += dedicatedAccountID.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 0
		os.write(0x80);
		codeLength += 1;
		
		codeLength += BerLength.encodeLength(os, codeLength);

		if (withTag) {
			codeLength += tag.encode(os);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		codeLength += totalLength;

		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
			dedicatedAccountID = new DedicatedAccountID();
			subCodeLength += dedicatedAccountID.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
			dedicatedAccountChange = new MonetaryUnits();
			subCodeLength += dedicatedAccountChange.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
			dedicatedAccountValueAfter = new MonetaryUnits();
			subCodeLength += dedicatedAccountValueAfter.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
			dedicatedAccountCampaignID = new DedicatedAccountCampaignID();
			subCodeLength += dedicatedAccountCampaignID.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (dedicatedAccountID != null) {
			sb.append("dedicatedAccountID: ").append(dedicatedAccountID);
		}
		else {
			sb.append("dedicatedAccountID: <empty-required-field>");
		}
		
		if (dedicatedAccountChange != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("dedicatedAccountChange: ");
			dedicatedAccountChange.appendAsString(sb, indentLevel + 1);
		}
		
		if (dedicatedAccountValueAfter != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("dedicatedAccountValueAfter: ");
			dedicatedAccountValueAfter.appendAsString(sb, indentLevel + 1);
		}
		
		if (dedicatedAccountCampaignID != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("dedicatedAccountCampaignID: ").append(dedicatedAccountCampaignID);
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}
