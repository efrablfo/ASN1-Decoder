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


public class Accumulator implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	private AccumulatorID accumulatorID = null;
	private Integer32 accumulatorBefore = null;
	private Integer32 accumulatorChange = null;
	private Integer32 accumulatorAfter = null;
	private Integer32 accumulatorBeforeSession = null;
	
	public Accumulator() {
	}

	public Accumulator(byte[] code) {
		this.code = code;
	}

	public void setAccumulatorID(AccumulatorID accumulatorID) {
		this.accumulatorID = accumulatorID;
	}

	public AccumulatorID getAccumulatorID() {
		return accumulatorID;
	}

	public void setAccumulatorBefore(Integer32 accumulatorBefore) {
		this.accumulatorBefore = accumulatorBefore;
	}

	public Integer32 getAccumulatorBefore() {
		return accumulatorBefore;
	}

	public void setAccumulatorChange(Integer32 accumulatorChange) {
		this.accumulatorChange = accumulatorChange;
	}

	public Integer32 getAccumulatorChange() {
		return accumulatorChange;
	}

	public void setAccumulatorAfter(Integer32 accumulatorAfter) {
		this.accumulatorAfter = accumulatorAfter;
	}

	public Integer32 getAccumulatorAfter() {
		return accumulatorAfter;
	}

	public void setAccumulatorBeforeSession(Integer32 accumulatorBeforeSession) {
		this.accumulatorBeforeSession = accumulatorBeforeSession;
	}

	public Integer32 getAccumulatorBeforeSession() {
		return accumulatorBeforeSession;
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
		if (accumulatorBeforeSession != null) {
			codeLength += accumulatorBeforeSession.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 4
			os.write(0x84);
			codeLength += 1;
		}
		
		codeLength += accumulatorAfter.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 3
		os.write(0x83);
		codeLength += 1;
		
		codeLength += accumulatorChange.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 2
		os.write(0x82);
		codeLength += 1;
		
		if (accumulatorBefore != null) {
			codeLength += accumulatorBefore.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 1
			os.write(0x81);
			codeLength += 1;
		}
		
		codeLength += accumulatorID.encode(os, false);
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
			accumulatorID = new AccumulatorID();
			subCodeLength += accumulatorID.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			accumulatorBefore = new Integer32();
			subCodeLength += accumulatorBefore.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
			accumulatorChange = new Integer32();
			subCodeLength += accumulatorChange.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
			accumulatorAfter = new Integer32();
			subCodeLength += accumulatorAfter.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 4)) {
			accumulatorBeforeSession = new Integer32();
			subCodeLength += accumulatorBeforeSession.decode(is, false);
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
		if (accumulatorID != null) {
			sb.append("accumulatorID: ").append(accumulatorID);
		}
		else {
			sb.append("accumulatorID: <empty-required-field>");
		}
		
		if (accumulatorBefore != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("accumulatorBefore: ").append(accumulatorBefore);
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (accumulatorChange != null) {
			sb.append("accumulatorChange: ").append(accumulatorChange);
		}
		else {
			sb.append("accumulatorChange: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (accumulatorAfter != null) {
			sb.append("accumulatorAfter: ").append(accumulatorAfter);
		}
		else {
			sb.append("accumulatorAfter: <empty-required-field>");
		}
		
		if (accumulatorBeforeSession != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("accumulatorBeforeSession: ").append(accumulatorBeforeSession);
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

